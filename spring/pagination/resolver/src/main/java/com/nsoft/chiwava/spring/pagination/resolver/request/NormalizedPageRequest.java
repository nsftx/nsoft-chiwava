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

package com.nsoft.chiwava.spring.pagination.resolver.request;

import com.nsoft.chiwava.spring.pagination.resolver.exception.InvalidPaginationParametersException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author Mislav Milicevic
 * @since 2020-01-22
 */
public final class NormalizedPageRequest {

    public static PageRequest of(int page, int size, Sort sort) {
        if (page < 1) {
            throw new InvalidPaginationParametersException("Page must not be less than one!");
        }
        return PageRequest.of(page - 1, size, sort);
    }

    public static PageRequest of(int page, int size) {
        return of(page, size, Sort.unsorted());
    }

    public static PageRequest of(int page, int size, Sort.Direction direction,
            String... properties) {
        return of(page, size, Sort.by(direction, properties));
    }

    private NormalizedPageRequest() {
    }
}
