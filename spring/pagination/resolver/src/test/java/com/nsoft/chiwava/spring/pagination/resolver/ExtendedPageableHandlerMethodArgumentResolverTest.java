/*
 * Copyright 2019-2020 NSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nsoft.chiwava.spring.pagination.resolver;

import static com.nsoft.chiwava.spring.pagination.resolver.ExtendedPageableHandlerMethodArgumentResolver.LIMIT_PARAMETER;
import static com.nsoft.chiwava.spring.pagination.resolver.ExtendedPageableHandlerMethodArgumentResolver.OFFSET_PARAMETER;
import static com.nsoft.chiwava.spring.pagination.resolver.ExtendedPageableHandlerMethodArgumentResolver.PAGE_PARAMETER;
import static com.nsoft.chiwava.spring.pagination.resolver.ExtendedPageableHandlerMethodArgumentResolver.SIZE_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.nsoft.chiwava.spring.pagination.resolver.exception.InvalidPaginationParametersException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.web.context.request.NativeWebRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class ExtendedPageableHandlerMethodArgumentResolverTest {

    private static final Map<String, Map<String, String>> successfulResolverCases = new HashMap<>();
    private static final Map<String, Map<String, String>> failedResolverCases = new HashMap<>();

    @BeforeAll
    static void setupResolverCases() {
        final Map<String, String> limitOffset_allCorrect = new HashMap<>();
        limitOffset_allCorrect.put(LIMIT_PARAMETER, "10");
        limitOffset_allCorrect.put(OFFSET_PARAMETER, "0");

        final Map<String, String> pageSize_allCorrect = new HashMap<>();
        pageSize_allCorrect.put(PAGE_PARAMETER, "1");
        pageSize_allCorrect.put(SIZE_PARAMETER, "10");

        successfulResolverCases.put("Testing default parameters", new HashMap<>());
        successfulResolverCases
                .put("Testing limit and offset with correct parameters", limitOffset_allCorrect);
        successfulResolverCases
                .put("Testing page and size with correct parameters", pageSize_allCorrect);

        final Map<String, String> limitOffset_limitIncorrect = new HashMap<>();
        limitOffset_limitIncorrect.put(LIMIT_PARAMETER, "0");
        limitOffset_limitIncorrect.put(OFFSET_PARAMETER, "0");

        final Map<String, String> limitOffset_offsetIncorrect = new HashMap<>();
        limitOffset_offsetIncorrect.put(LIMIT_PARAMETER, "10");
        limitOffset_offsetIncorrect.put(OFFSET_PARAMETER, "-1");

        final Map<String, String> limitOffset_limitAndOffsetIncorrect = new HashMap<>();
        limitOffset_limitAndOffsetIncorrect.put(LIMIT_PARAMETER, "0");
        limitOffset_limitAndOffsetIncorrect.put(OFFSET_PARAMETER, "-1");

        final Map<String, String> missingParameter_limit = new HashMap<>();
        missingParameter_limit.put(OFFSET_PARAMETER, "0");

        final Map<String, String> missingParameter_offset = new HashMap<>();
        missingParameter_offset.put(LIMIT_PARAMETER, "10");

        final Map<String, String> missingParameter_page = new HashMap<>();
        missingParameter_page.put(SIZE_PARAMETER, "10");

        final Map<String, String> missingParameter_size = new HashMap<>();
        missingParameter_size.put(PAGE_PARAMETER, "1");

        final Map<String, String> parameterMismatch_limitPage = new HashMap<>();
        parameterMismatch_limitPage.put(LIMIT_PARAMETER, "10");
        parameterMismatch_limitPage.put(PAGE_PARAMETER, "1");

        final Map<String, String> parameterMismatch_limitSize = new HashMap<>();
        parameterMismatch_limitSize.put(LIMIT_PARAMETER, "10");
        parameterMismatch_limitSize.put(SIZE_PARAMETER, "10");

        final Map<String, String> parameterMismatch_offsetPage = new HashMap<>();
        parameterMismatch_offsetPage.put(OFFSET_PARAMETER, "0");
        parameterMismatch_offsetPage.put(PAGE_PARAMETER, "1");

        final Map<String, String> parameterMismatch_offsetSize = new HashMap<>();
        parameterMismatch_offsetSize.put(OFFSET_PARAMETER, "0");
        parameterMismatch_offsetSize.put(SIZE_PARAMETER, "10");

        failedResolverCases
                .put("Testing limit and offset with incorrect limit", limitOffset_limitIncorrect);
        failedResolverCases
                .put("Testing limit and offset with incorrect offset", limitOffset_offsetIncorrect);
        failedResolverCases.put("Testing limit and offset with incorrect limit and offset",
                limitOffset_limitAndOffsetIncorrect);
        failedResolverCases.put("Testing missing parameter with limit", missingParameter_limit);
        failedResolverCases.put("Testing missing parameter with offset", missingParameter_offset);
        failedResolverCases.put("Testing missing parameter with page", missingParameter_page);
        failedResolverCases.put("Testing missing parameter with size", missingParameter_size);
        failedResolverCases
                .put("Testing parameter mismatch with limit and page", parameterMismatch_limitPage);
        failedResolverCases
                .put("Testing parameter mismatch with limit and size", parameterMismatch_limitSize);
        failedResolverCases.put("Testing parameter mismatch with offset and page",
                parameterMismatch_offsetPage);
        failedResolverCases.put("Testing parameter mismatch with offset and size",
                parameterMismatch_offsetSize);
    }

    @TestFactory
    Stream<DynamicTest> resolveArgument() {
        final Stream<DynamicTest> successfulCasesStream = successfulResolverCases.entrySet()
                .stream()
                .map(entry -> dynamicTest(entry.getKey(), () -> {
                    final ExtendedPageableHandlerMethodArgumentResolver resolver = new ExtendedPageableHandlerMethodArgumentResolver();

                    final NativeWebRequest request = mock(NativeWebRequest.class);
                    final Map<String, String> caseParameters = entry.getValue();

                    caseParameters
                            .forEach((key, value) -> doReturn(value).when(request)
                                    .getParameter(key));

                    assertDoesNotThrow(() -> resolver.resolveArgument(null, null, request, null));
                }));

        final Stream<DynamicTest> failedCasesStream = failedResolverCases.entrySet().stream()
                .map(entry -> dynamicTest(entry.getKey(), () -> {
                    final ExtendedPageableHandlerMethodArgumentResolver resolver = new ExtendedPageableHandlerMethodArgumentResolver();

                    final NativeWebRequest request = mock(NativeWebRequest.class);
                    final Map<String, String> caseParameters = entry.getValue();

                    caseParameters
                            .forEach((key, value) -> doReturn(value).when(request)
                                    .getParameter(key));

                    assertThrows(InvalidPaginationParametersException.class,
                            () -> resolver.resolveArgument(null, null, request, null));
                }));

        return Stream.concat(successfulCasesStream, failedCasesStream);
    }

    @Test
    void shouldCreateSort()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String[] properties = {"up", "+explicitUp", "-down"};

        final ExtendedPageableHandlerMethodArgumentResolver resolver = new ExtendedPageableHandlerMethodArgumentResolver();

        final Method method = resolver.getClass()
                .getDeclaredMethod("buildSort", properties.getClass());
        method.setAccessible(true);

        final JpaSort sort = (JpaSort) method.invoke(resolver, new Object[]{properties});
        assertNotNull(sort);
    }
}
