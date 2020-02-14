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

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;

import com.nsoft.chiwava.spring.pagination.resolver.exception.InvalidPaginationParametersException;
import com.nsoft.chiwava.spring.pagination.resolver.request.NormalizedPageRequest;
import com.nsoft.chiwava.spring.pagination.resolver.request.OffsetLimitPageRequest;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashSet;
import java.util.Set;

/**
 * Used to resolve pagination arguments in HTTP requests
 *
 * <pre>
 *     public class SpringFoxConfig extends WebMvcConfigurationSupport {
 *         ...
 *         &#64;Override
 *         protected void addArgumentResolvers(List&#60;HandlerMethodArgumentResolver&#62; argumentResolvers) {
 *              ExtendedPageableHandlerMethodArgumentResolver resolver =
 *                new ExtendedPageableHandlerMethodArgumentResolver();
 *              argumentResolvers.add(resolver);
 *              super.addArgumentResolvers(argumentResolvers);
 *         }
 *         ...
 *     }
 * </pre>
 *
 * @author Nikola Rakic
 * @author Mislav Milicevic
 * @since 2019-09-06
 */
public class ExtendedPageableHandlerMethodArgumentResolver extends
        PageableHandlerMethodArgumentResolver {

    private static final int DEFAULT_SIZE = 10;
    private static final int DEFAULT_PAGE = 1;

    static final String LIMIT_PARAMETER = "limit";
    static final String OFFSET_PARAMETER = "offset";
    static final String PAGE_PARAMETER = "page";
    static final String SIZE_PARAMETER = "size";
    static final String SORT_PARAMETER = "sort";

    @Override
    public Pageable resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {
        PageableParameters pageableParameters = parsePageableParameters(webRequest);

        if (pageableParameters.hasMixedStrategies()) {
            throw new InvalidPaginationParametersException(
                    "Choose one strategy: page/size or offset/limit");
        }

        if (pageableParameters.hasUnpairedLimitAndOffset()) {
            throw new InvalidPaginationParametersException(
                    "Both limit and offset parameters must be provided");
        }

        if (pageableParameters.hasUnpairedPageAndSize()) {
            throw new InvalidPaginationParametersException(
                    "Both page and size parameters must be provided");
        }

        if (pageableParameters.hasOnlyLimitAndOffset()) {
            return OffsetLimitPageRequest.of(
                    pageableParameters.getOffset(),
                    pageableParameters.getLimit(),
                    pageableParameters.getSort() != null
                            ? pageableParameters.getSort()
                            : Sort.unsorted());
        }

        if (pageableParameters.hasOnlyPageAndSize()) {
            return NormalizedPageRequest.of(
                    pageableParameters.getPage(),
                    pageableParameters.getSize(),
                    pageableParameters.getSort() != null
                            ? pageableParameters.getSort()
                            : Sort.unsorted());
        }

        return NormalizedPageRequest.of(
                DEFAULT_PAGE,
                DEFAULT_SIZE,
                pageableParameters.getSort() != null
                        ? pageableParameters.getSort()
                        : Sort.unsorted());
    }

    private PageableParameters parsePageableParameters(NativeWebRequest request) {
        Integer limit = null;
        Integer offset = null;
        Integer page = null;
        Integer size = null;
        JpaSort sort = null;

        if (parameterExists(request, LIMIT_PARAMETER)) {
            limit = parseInt(requireNonNull(request.getParameter(LIMIT_PARAMETER)));
        }

        if (parameterExists(request, OFFSET_PARAMETER)) {
            offset = parseInt(requireNonNull(request.getParameter(OFFSET_PARAMETER)));
        }

        if (parameterExists(request, PAGE_PARAMETER)) {
            page = parseInt(requireNonNull(request.getParameter(PAGE_PARAMETER)));
        }

        if (parameterExists(request, SIZE_PARAMETER)) {
            size = parseInt(requireNonNull(request.getParameter(SIZE_PARAMETER)));
        }

        if (parameterExists(request, SORT_PARAMETER)) {
            sort = buildSort(request.getParameterValues(SORT_PARAMETER));
        }

        return new PageableParameters(limit, offset, page, size, sort);
    }

    private JpaSort buildSort(String[] properties) {
        Set<String> ascProperties = new HashSet<>();
        Set<String> descProperties = new HashSet<>();

        JpaSort jpaSort = null;

        for (String property : requireNonNull(properties)) {
            property = property.trim();

            Sort.Direction direction =
                    property.startsWith("-") ? Direction.DESC : Direction.ASC;

            property = property
                    .substring((property.startsWith("-") || property.startsWith("+")) ? 1 : 0);

            if (direction == Direction.ASC) {
                if (descProperties.contains(property)) {
                    throw new InvalidPaginationParametersException(
                            "Can't sort by same parameter in multiple directions");
                }
                ascProperties.add(property);
            } else {
                if (ascProperties.contains(property)) {
                    throw new InvalidPaginationParametersException(
                            "Can't sort by same parameter in multiple directions");
                }
                descProperties.add(property);
            }

            if (jpaSort == null) {
                jpaSort = JpaSort.unsafe(direction, property);
            } else {
                jpaSort = jpaSort.andUnsafe(direction, property);
            }
        }

        return jpaSort;
    }

    private boolean parameterExists(NativeWebRequest request, String parameterName) {
        final String parameter = request.getParameter(parameterName);

        return parameter != null && !parameter.isEmpty();
    }
}
