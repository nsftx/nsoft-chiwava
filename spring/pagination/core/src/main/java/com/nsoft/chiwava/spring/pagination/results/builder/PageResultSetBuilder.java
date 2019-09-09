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
