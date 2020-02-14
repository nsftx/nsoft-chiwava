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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.nsoft.chiwava.support.MockSupport;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

final class PaginationTest {

    @Test
    void fromPage() {
        final int pageSize = 10;
        final long totalElements = 100;
        final int totalPages = (int) (totalElements / pageSize);

        final Page<?> page = MockSupport.mockPage(pageSize, totalElements);

        final Pagination pagination = Pagination.fromPage(page);

        assertNotNull(pagination);
        assertEquals(pageSize, pagination.getSize());
        assertEquals(pageSize, pagination.getLimit());
        assertEquals(totalElements, page.getTotalElements());
        assertEquals(totalPages, pagination.getPages());
        assertFalse(pagination.getPage() > pagination.getPages());
        assertEquals(page.getSize() * page.getNumber(), pagination.getOffset());
    }
}
