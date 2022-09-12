package com.example.base.common;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
/** * Base tra ve du lieu phan trang * @param <T> */
@Getter
@Setter
public class RestResponsePage<T> {
    private Pagination pagination;
    private List<T> content;

    public RestResponsePage(List<T> content, int page, int size, int numberOfElements, int totalPages) {
        this.content = content;
        Pagination pagination = new Pagination();
        pagination.currentPage = page;
        pagination.size = size;
        pagination.totalPages = totalPages;
        pagination.numberOfElements = numberOfElements;
        this.pagination = pagination;
    }

    public RestResponsePage(List<T> content, int page, int size, long numberOfElements, int totalPages) {
        this.content = content;
        Pagination pagination = new Pagination();
        pagination.currentPage = page;
        pagination.size = size;
        pagination.totalPages = totalPages;
        pagination.numberOfElements = numberOfElements;
        this.pagination = pagination;
    }

    @Getter
    @Setter
    public class Pagination {
        private int currentPage;
        private int size;
        private int totalPages;
        private long numberOfElements;
    }
}
