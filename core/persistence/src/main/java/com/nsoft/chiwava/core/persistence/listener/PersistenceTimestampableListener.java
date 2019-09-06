package com.nsoft.chiwava.core.persistence.listener;

import com.nsoft.chiwava.core.persistence.PersistenceTimestampable;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Used with {@link javax.persistence.EntityListeners} to timestamp entities when they are created
 * and update
 *
 * @author Mislav Milicevic
 * @since 2019-09-06
 */
public class PersistenceTimestampableListener {

    @PrePersist
    public void prePersist(PersistenceTimestampable persistenceTimestampable) {
        if (persistenceTimestampable.getCreatedAt() == null)
            persistenceTimestampable.setCreatedAt();
        if (persistenceTimestampable.getUpdatedAt() == null) {
            persistenceTimestampable.setUpdatedAt();
        }
    }

    @PreUpdate
    public void preUpdate(PersistenceTimestampable persistenceTimestampable) {
        persistenceTimestampable.setUpdatedAt();
    }

}
