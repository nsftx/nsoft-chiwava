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

package com.nsoft.chiwava.spring.pagination;

import static java.util.Objects.requireNonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

/**
 * A metadata object used to provide additional information about the pagination configuration used
 * by a data set. If a data set is paginated, it is expected that a {@link Pagination} object is
 * present in the result set in order to provide additional context.
 *
 * @author Mislav Milicevic
 * @since 2019-09-06
 */
@Getter
@AllArgsConstructor
public final class Pagination {

    private final Integer size;
    private final Integer limit;
    private final Long total;
    private final Integer page;
    private final Integer pages;
    private final Long offset;

    /**
     * Constructs and returns a {@link Pagination} instance from a Spring {@link Page}
     *
     * @param page {@link Page} instance
     * @return {@link Pagination} instance constructed from a Spring {@link Page}
     */
    public static Pagination fromPage(Page<?> page) {
        requireNonNull(page, "page can't be null");

        return new Pagination(
                page.getSize(),
                page.getSize(),
                page.getTotalElements(),
                page.getNumber() + 1,
                page.getTotalPages(),
                page.getPageable().isPaged() ? page.getPageable().getOffset() : 0
        );
    }
}
