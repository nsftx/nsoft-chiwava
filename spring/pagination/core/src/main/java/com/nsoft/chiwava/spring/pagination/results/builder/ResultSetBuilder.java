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
