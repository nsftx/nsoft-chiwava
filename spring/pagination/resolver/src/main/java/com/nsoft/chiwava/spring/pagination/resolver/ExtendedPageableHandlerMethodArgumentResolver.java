/*
 * Copyright 2019 NSoft
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

import com.nsoft.chiwava.spring.pagination.resolver.exception.InvalidPaginationParametersException;
import com.nsoft.chiwava.spring.pagination.resolver.request.OffsetLimitPageRequest;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Used to resolve pagination arguments in http requests
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
 */
public class ExtendedPageableHandlerMethodArgumentResolver extends
        PageableHandlerMethodArgumentResolver {

    private static final int DEFAULT_SIZE = 10;
    private static final int DEFAULT_PAGE = 0;

    private static final String LIMIT_PARAMETER = "limit";
    private static final String OFFSET_PARAMETER = "offset";
    private static final String PAGE_PARAMETER = "page";
    private static final String SIZE_PARAMETER = "size";
    private static final String SORT_PARAMETER = "sort";

    private Integer limit;
    private Integer offset;
    private Integer page;
    private Integer size;
    private JpaSort sort;

    @Override
    public Pageable resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {
        parsePageableParameters(webRequest);

        if (hasMixedStrategies()) {
            throw new InvalidPaginationParametersException(
                    "Choose one strategy: page/size or offset/limit");
        }

        if (hasUnpairedLimitAndOffset()) {
            throw new InvalidPaginationParametersException(
                    "Both limit and offset parameters must be provided");
        }

        if (hasUnpairedPageAndSize()) {
            throw new InvalidPaginationParametersException(
                    "Both page and size parameters must be provided");
        }

        if (hasOnlyLimitAndOffset()) {
            return OffsetLimitPageRequest.of(offset, limit, sort != null ? sort : Sort.unsorted());
        }

        if (hasOnlyPageAndSize()) {
            return PageRequest.of(page, size, sort != null ? sort : Sort.unsorted());
        }

        return PageRequest.of(DEFAULT_PAGE, DEFAULT_SIZE, sort != null ? sort : Sort.unsorted());
    }

    private void parsePageableParameters(NativeWebRequest request) {
        limit = null;
        offset = null;
        page = null;
        size = null;
        sort = null;

        if (request.getParameter(LIMIT_PARAMETER) != null
                && !Objects.requireNonNull(request.getParameter(LIMIT_PARAMETER)).isEmpty()) {
            limit = Integer.parseInt(Objects.requireNonNull(request.getParameter(LIMIT_PARAMETER)));
        }

        if (request.getParameter(OFFSET_PARAMETER) != null
                && !Objects.requireNonNull(request.getParameter(OFFSET_PARAMETER)).isEmpty()) {
            offset = Integer
                    .parseInt(Objects.requireNonNull(request.getParameter(OFFSET_PARAMETER)));
        }

        if (request.getParameter(PAGE_PARAMETER) != null
                && !Objects.requireNonNull(request.getParameter(PAGE_PARAMETER)).isEmpty()) {
            page = Integer.parseInt(Objects.requireNonNull(request.getParameter(PAGE_PARAMETER)))
                    - 1;
        }

        if (request.getParameter(SIZE_PARAMETER) != null
                && !Objects.requireNonNull(request.getParameter(SIZE_PARAMETER)).isEmpty()) {
            size = Integer.parseInt(Objects.requireNonNull(request.getParameter(SIZE_PARAMETER)));
        }

        if (request.getParameterValues(SORT_PARAMETER) != null && !isPropertiesArrayBlank(
                request.getParameterValues(SORT_PARAMETER))) {
            sort = buildSort(request.getParameterValues(SORT_PARAMETER));
        }
    }

    private JpaSort buildSort(String[] properties) {
        Set<String> ascProperties = new HashSet<>();
        Set<String> descProperties = new HashSet<>();

        JpaSort jpaSort = null;

        for (String property : Objects.requireNonNull(properties)) {
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

    private boolean hasLimitAndOffset() {
        return limit != null && offset != null;
    }

    private boolean hasPageAndSize() {
        return page != null && size != null;
    }

    private boolean hasLimitOrOffset() {
        return limit != null || offset != null;
    }

    private boolean hasPageOrSize() {
        return page != null || size != null;
    }

    private boolean hasOnlyLimitAndOffset() {
        return hasLimitAndOffset() && !hasPageOrSize();
    }

    private boolean hasOnlyPageAndSize() {
        return !hasLimitOrOffset() && hasPageAndSize();
    }

    private boolean hasUnpairedLimitAndOffset() {
        return limit != null ^ offset != null;
    }

    private boolean hasUnpairedPageAndSize() {
        return page != null ^ size != null;
    }

    private boolean hasMixedStrategies() {
        return hasLimitOrOffset() && hasPageOrSize();
    }

    private boolean isPropertiesArrayBlank(String[] properties) {
        if (properties == null) {
            return true;
        }
        return properties.length == 1 && properties[0].isEmpty();
    }
}
