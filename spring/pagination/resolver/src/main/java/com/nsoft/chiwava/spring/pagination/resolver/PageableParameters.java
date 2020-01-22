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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.JpaSort;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class PageableParameters {

    private Integer limit;
    private Integer offset;
    private Integer page;
    private Integer size;
    private JpaSort sort;

    private boolean hasLimitAndOffset() {
        return limit != null && offset != null;
    }

    public boolean hasPageAndSize() {
        return page != null && size != null;
    }

    public boolean hasLimitOrOffset() {
        return limit != null || offset != null;
    }

    public boolean hasPageOrSize() {
        return page != null || size != null;
    }

    public boolean hasOnlyLimitAndOffset() {
        return hasLimitAndOffset() && !hasPageOrSize();
    }

    public boolean hasOnlyPageAndSize() {
        return !hasLimitOrOffset() && hasPageAndSize();
    }

    public boolean hasUnpairedLimitAndOffset() {
        return limit != null ^ offset != null;
    }

    public boolean hasUnpairedPageAndSize() {
        return page != null ^ size != null;
    }

    public boolean hasMixedStrategies() {
        return hasLimitOrOffset() && hasPageOrSize();
    }

}
