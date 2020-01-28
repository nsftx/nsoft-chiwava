/*
 * Copyright 2019 NSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nsoft.chiwava.core.persistence.listener;

import com.nsoft.chiwava.core.persistence.PersistenceCreateTimestampable;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

/**
 * Used with {@link javax.persistence.EntityListeners} to timestamp entities when they are created
 *
 * @author Mislav Milicevic
 * @since 2020-01-22
 */
public class PersistenceCreateTimestampableListener {

    @PrePersist
    public void prePersist(PersistenceCreateTimestampable timestampable) {
        if (timestampable.getCreatedAt() == null)
            timestampable.setCreatedAt(LocalDateTime.now());
    }

}
