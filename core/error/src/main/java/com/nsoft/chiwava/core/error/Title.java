package com.nsoft.chiwava.core.error;

/**
 * {@link Error} title abstraction
 *
 * <pre>
 *     public enum ExceptionTitles implements Title {
 *          RESOURCE_NOT_FOUND("exception.resource_not_found")
 *         ;
 *
 *         private final String title;
 *
 *         ErrorTitles(String title) {
 *             this.title = title;
 *         }
 *
 *         &#64;Override
 *         public String toString() {
 *             return this.title;
 *         }
 *     }
 * </pre>
 *
 * @author Mislav Milicevic
 * @since 2019-09-06
 */
public interface Title {

    String toString();
}
