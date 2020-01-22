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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Nikola Rakic
 * @since 2019-09-06
 */
public class OffsetLimitPageRequest implements Pageable, Serializable {

    private static final long serialVersionUID = 5761669087831551553L;

    private long offset;
    private int limit;
    private final Sort sort;

    /**
     * Creates a new {@link OffsetLimitPageRequest} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit the size of the elements to be returned.
     * @param sort can be {@literal null}.
     */
    private OffsetLimitPageRequest(long offset, int limit, Sort sort) {
        if (offset < 0) {
            throw new InvalidPaginationParametersException(
                    "Offset index must not be less than zero!");
        }

        if (limit < 1) {
            throw new InvalidPaginationParametersException("Limit must not be less than one!");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    /**
     * Creates a new {@link OffsetLimitPageRequest} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit the size of the elements to be returned.
     * @param direction the direction of the {@link Sort} to be specified, can be {@literal null}.
     * @param properties the properties to sort by, must not be {@literal null} or empty.
     */
    public static OffsetLimitPageRequest of(long offset, int limit, Sort.Direction direction,
            String... properties) {
        return new OffsetLimitPageRequest(offset, limit, Sort.by(direction, properties));
    }

    /**
     * Creates a new {@link OffsetLimitPageRequest} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit the size of the elements to be returned.
     * @param sort sort object.
     */
    public static OffsetLimitPageRequest of(long offset, int limit, Sort sort) {
        return new OffsetLimitPageRequest(offset, limit, sort);
    }

    /**
     * Creates a new {@link OffsetLimitPageRequest} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit the size of the elements to be returned.
     */
    public static OffsetLimitPageRequest of(long offset, int limit) {
        return new OffsetLimitPageRequest(offset, limit, Sort.unsorted());
    }

    @Override
    public int getPageNumber() {
        return (int) (offset / limit);
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetLimitPageRequest(getOffset() + getPageSize(), getPageSize(), getSort());
    }

    public OffsetLimitPageRequest previous() {
        return hasPrevious()
                ? new OffsetLimitPageRequest(getOffset() - getPageSize(), getPageSize(), getSort())
                : this;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new OffsetLimitPageRequest(0, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OffsetLimitPageRequest that = (OffsetLimitPageRequest) o;

        if (offset != that.offset) return false;
        if (limit != that.limit) return false;
        return Objects.equals(sort, that.sort);
    }

    @Override
    public int hashCode() {
        int result = sort != null ? sort.hashCode() : 0;
        result = 31 * result + (int) (offset ^ (offset >>> 32));
        result = 31 * result + limit;
        return result;
    }

    @Override
    public String toString() {
        return "OffsetLimitPageRequest{" +
                "sort=" + sort +
                ", offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
