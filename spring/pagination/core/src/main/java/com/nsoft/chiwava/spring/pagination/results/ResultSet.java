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

package com.nsoft.chiwava.spring.pagination.results;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nsoft.chiwava.spring.pagination.Pagination;
import lombok.Getter;

import java.util.List;

/**
 * Represents a paginated set of results
 *
 * @author Mislav Milicevic
 * @since 2019-09-09
 */
@Getter
public class ResultSet<T> {
    private final Pagination pagination;
    private final List<T> data;

    @JsonCreator
    public ResultSet(@JsonProperty("pagination") Pagination pagination,
            @JsonProperty("data") List<T> data) {
        this.pagination = pagination;
        this.data = data;
    }
}
