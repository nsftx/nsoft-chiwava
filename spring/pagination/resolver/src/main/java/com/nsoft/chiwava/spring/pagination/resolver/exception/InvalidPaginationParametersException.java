package com.nsoft.chiwava.spring.pagination.resolver.exception;

public class InvalidPaginationParametersException extends RuntimeException {
    public InvalidPaginationParametersException() {
        super();
    }

    public InvalidPaginationParametersException(String details) {
        super(details);
    }
}
