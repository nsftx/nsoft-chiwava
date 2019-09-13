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

package com.nsoft.chiwava.spring.pagination.results.builder;

import com.nsoft.chiwava.spring.pagination.results.ResultSet;

/**
 * Used to construct result sets from a collection of specified inputs
 *
 * @param <I> input, usually an entity
 * @param <O> output, usually a view model
 * @param <C> input collection
 *
 * @author Mislav Milicevic
 * @since 2019-09-09
 */
public interface ResultSetBuilder<I, O, C extends Iterable<I>> {

    /**
     * Constructs a result set from a specified collection
     *
     * @param collection input collection
     * @return result set of specified output objects
     */
    ResultSet<O> build(C collection);

    /**
     * Constructs a specified output object from a specified input object
     *
     * @param input input object
     * @return output object
     */
    O buildSingle(I input);
}
