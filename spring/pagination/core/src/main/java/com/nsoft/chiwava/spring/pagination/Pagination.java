package com.nsoft.chiwava.spring.pagination;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

/**
 * Pagination representation
 *
 * @author Mislav Milicevic
 * @since 2019-06-09
 */
@Getter
@Setter
public final class Pagination {

    private Integer page;
    private Integer size;
    private Long offset;
    private Integer limit;
    private Long total;
    private Integer pages;

    public static Pagination fromPage(Page<?> page) {
        Pagination pagination = new Pagination();
        pagination.setSize(page.getSize());
        pagination.setLimit(page.getSize());
        pagination.setTotal(page.getTotalElements());
        pagination.setPage(page.getNumber() + 1);
        pagination.setPages(page.getTotalPages());
        pagination.setOffset(page.getPageable().isPaged() ? page.getPageable().getOffset() : 0);
        return pagination;
    }
}
