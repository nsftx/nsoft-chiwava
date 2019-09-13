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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        if (request.getParameter("limit") != null
                && !Objects.requireNonNull(request.getParameter("limit")).isBlank()) {
            limit = Integer.parseInt(Objects.requireNonNull(request.getParameter("limit")));
        }

        if (request.getParameter("offset") != null
                && !Objects.requireNonNull(request.getParameter("offset")).isBlank()) {
            offset = Integer.parseInt(Objects.requireNonNull(request.getParameter("offset")));
        }

        if (request.getParameter("page") != null
                && !Objects.requireNonNull(request.getParameter("page")).isBlank()) {
            page = Integer.parseInt(Objects.requireNonNull(request.getParameter("page"))) - 1;
        }

        if (request.getParameter("size") != null
                && !Objects.requireNonNull(request.getParameter("size")).isBlank()) {
            size = Integer.parseInt(Objects.requireNonNull(request.getParameter("size")));
        }

        if (request.getParameterValues("sort") != null && Objects
                .requireNonNull(request.getParameterValues("sort")).length != 0) {
            String[] properties = request.getParameterValues("sort");

            List<String> ascProperties = new ArrayList<>();
            List<String> descProperties = new ArrayList<>();

            for (String property : Objects.requireNonNull(properties)) {
                property = property.trim();

                Sort.Direction direction =
                        property.startsWith("-") ? Direction.DESC : Direction.ASC;

                if (property.startsWith("-") || property.startsWith("+")) {
                    property = property.substring(1);
                }

                if (ascProperties.contains(property) || descProperties.contains(property)) {
                    throw new InvalidPaginationParametersException(
                            "Can't sort by same parameter in multiple directions");
                }

                if (direction == Direction.ASC) {
                    ascProperties.add(property);
                } else {
                    descProperties.add(property);
                }

                if (sort == null) {
                    sort = JpaSort.unsafe(direction, property);
                } else {
                    sort = sort.andUnsafe(direction, property);
                }
            }
        }
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
}
