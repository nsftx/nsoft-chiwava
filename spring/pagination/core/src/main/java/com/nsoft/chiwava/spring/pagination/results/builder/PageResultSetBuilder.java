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

package com.nsoft.chiwava.spring.pagination.results.builder;

import com.nsoft.chiwava.spring.pagination.Pagination;
import com.nsoft.chiwava.spring.pagination.results.ResultSet;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * An abstract result set builder implementation that uses a Spring {@link Page} as an input
 * collection
 *
 * @author Mislav Milicevic
 * @since 2019-09-09
 */
public abstract class PageResultSetBuilder<I, O> implements ResultSetBuilder<I, O, Page<I>> {

    public ResultSet<O> build(Page<I> page) {
        Pagination pagination = Pagination.fromPage(page);

        List<O> data = page.stream().map(this::buildSingle).collect(
                Collectors.toList());

        return new ResultSet<>(pagination, data);
    }

}
