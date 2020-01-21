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

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

/**
 * Pagination representation
 *
 * @author Mislav Milicevic
 * @since 2019-09-06
 */
@Getter
@Setter
public final class Pagination {

    private Integer page;
    private Integer size;
    private Long offset;
    private Integer limit;
    private Long total;
    private Integer pages;

    public static Pagination fromPage(Page<?> page) {
        Pagination pagination = new Pagination();
        pagination.setSize(page.getSize());
        pagination.setLimit(page.getSize());
        pagination.setTotal(page.getTotalElements());
        pagination.setPage(page.getNumber() + 1);
        pagination.setPages(page.getTotalPages());
        pagination.setOffset(page.getPageable().isPaged() ? page.getPageable().getOffset() : 0);
        return pagination;
    }
}
