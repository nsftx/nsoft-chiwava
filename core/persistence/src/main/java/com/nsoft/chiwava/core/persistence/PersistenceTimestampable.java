package com.nsoft.chiwava.core.persistence;

import java.time.LocalDateTime;

/**
 * Used as a pre-persistence layer to timestamp entities when they are created and updated
 *
 * The implementing class needs be annotated with {@link javax.persistence.EntityListeners} with
 * {@link com.nsoft.chiwava.core.persistence.listener.PersistenceTimestampableListener} as an
 * argument to enable pre-persist timestamping
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
