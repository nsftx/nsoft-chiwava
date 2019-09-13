package com.nsoft.chiwava.core.error;

/**
 * Abstract implementation of {@link Error}
 *
 * <pre>
 *     public class ResourceNotFoundException extends AbstractError {
 *
 *         &#64;Override
 *         public Status getStatus() {
 *             return Status.NOT_FOUND;
 *         }
 *
 *         &#64;Override
 *         public Title getTitle() {
 *             return ErrorTitles.RESOURCE_NOT_FOUND;
 *         }
 *     }
 * </pre>
 *
 * @author Mislav Milicevic
 * @since 2019-09-06
 */
public abstract class AbstractError extends RuntimeException implements Error {
    public AbstractError() {
        super();
    }

    public AbstractError(String message) {
        super(message);
    }
}
