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

package com.nsoft.chiwava.support;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.data.domain.Page;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public final class MockSupport {

    @SuppressWarnings("unchecked")
    public static Page<Object> mockPage(int pageSize, long totalElements) {
        final int totalPages = (int) (totalElements / pageSize);
        final int pageNumber = randomPageNumber(totalPages);
        final long offset = (long) pageSize * pageNumber;

        final Page<Object> page = mock(Page.class, RETURNS_DEEP_STUBS);
        when(page.getSize()).thenReturn(pageSize);
        when(page.getTotalElements()).thenReturn(totalElements);
        when(page.getTotalPages()).thenReturn(totalPages);
        when(page.getNumber()).thenReturn(pageNumber);
        when(page.getPageable().isPaged()).thenReturn(true);
        when(page.getPageable().getOffset()).thenReturn(offset);
        when(page.stream()).thenReturn(
                Stream.of("gKi8JFOauk", "Lh39Nw6tjb", "i5KIBQmDrV", "x0z8RFwqtp", "7YbITKRJXp",
                        "P0VoWLRtrK", "x5wF9IpRns", "nYe3Jw8frP", "pC3MBa026H", "IsmzDLK0RJ"));

        return page;
    }

    private static int randomPageNumber(int totalPages) {
        return ThreadLocalRandom.current().nextInt(1, totalPages);
    }

    private MockSupport() {
    }
}
