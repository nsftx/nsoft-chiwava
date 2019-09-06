package com.nsoft.chiwava.core.error;

import org.zalando.problem.Status;

/**
 * An error model used within REST APIs
 *
 * @author Mislav Milicevic
 * @since 2019-09-06
 */
public interface Error {

    /**
     * Returns the response status from a REST call
     *
     * @return response status
     */
    Status getStatus();

    /**
     * Returns the error title
     *
     * @return title
     */
    Title getTitle();

    /**
     * Returns the error message
     *
     * @return message
     */
    String getMessage();
}
