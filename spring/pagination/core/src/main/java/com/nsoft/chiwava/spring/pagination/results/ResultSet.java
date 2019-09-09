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
