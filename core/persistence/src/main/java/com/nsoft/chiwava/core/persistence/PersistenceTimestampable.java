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

package com.nsoft.chiwava.core.persistence;

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
public interface PersistenceTimestampable extends PersistenceCreateTimestampable, PersistenceUpdateTimestampable {
}
