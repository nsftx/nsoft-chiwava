package com.nsoft.chiwava.spring.pagination.resolver.exception;

import com.nsoft.chiwava.core.error.AbstractError;
import com.nsoft.chiwava.core.error.Title;
import org.zalando.problem.Status;

public class InvalidPaginationParametersException extends AbstractError {

    private static final Title EXCEPTION_TITLE = new Title() {
        @Override
        public String toString() {
            return "exception.invalid_pagination_parameters";
        }
    };

    public InvalidPaginationParametersException(String details) {
        super(details);
    }

    @Override
    public Status getStatus() {
        return Status.BAD_REQUEST;
    }

    @Override
    public Title getTitle() {
        return EXCEPTION_TITLE;
    }
}
