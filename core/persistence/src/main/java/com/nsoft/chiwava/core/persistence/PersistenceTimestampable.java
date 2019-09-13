package com.nsoft.chiwava.core.persistence;

import java.time.LocalDateTime;

/**
 * Used as a pre-persistence layer to timestamp entities when they are created and updated
 *
 * The implementing class needs be annotated with {@link javax.persistence.EntityListeners} with
 * {@link com.nsoft.chiwava.core.persistence.listener.PersistenceTimestampableListener} as an
 * argument to enable pre-persist timestamping
 *
 * <pre>
 *     &#64;Entity
 *     &#64;EntityListeners(PersistenceTimestampableListener.class)
 *     &#64;Getter
 *     public class SimpleEntity implements PersistenceTimestampable {
 *
 *          &#64;Id
 *          &#64;GeneratedValue(strategy = GenerationType.IDENTITY)
 *          &#64;Column(name = "id", nullable = false, updatable = false)
 *          private Long id;
 *
 *          &#64;Column(name = "created_at", nullable = false, updatable = false)
 *          &#64;Setter
 *          private LocalDateTime createdAt;
 *
 *          &#64;Column(name = "updated_at", nullable = false)
 *          &#64;Setter
 *          private LocalDateTime updatedAt;
 *
 *          public SimpleEntity() {
 *          }
 *     }
 * </pre>
 *
 * @author Mislav Milicevic
 * @since 2019-09-06
 */
public interface PersistenceTimestampable {

    LocalDateTime getCreatedAt();

    void setCreatedAt(LocalDateTime localDateTime);

    LocalDateTime getUpdatedAt();

    void setUpdatedAt(LocalDateTime localDateTime);

    default void setCreatedAt() {
        setCreatedAt(LocalDateTime.now());
    }

    default void setUpdatedAt() {
        setUpdatedAt(LocalDateTime.now());
    }
}
