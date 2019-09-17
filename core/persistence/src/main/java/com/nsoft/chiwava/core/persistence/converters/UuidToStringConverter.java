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

package com.nsoft.chiwava.core.persistence.converters;

import javax.persistence.AttributeConverter;
import java.util.UUID;

/**
 * Converts UUID properties inside entities to Strings
 *
 * <pre>
 *     &#64;Entity
 *     &#64;Getter
 *     public class SimpleEntity implements PersistenceTimestampable {
 *
 *          &#64;Id
 *          &#64;GeneratedValue(strategy = GenerationType.IDENTITY)
 *          &#64;Column(name = "id", nullable = false, updatable = false)
 *          private Long id;
 *
 *          &#64;Column(name = "uuid", nullable = false, updatable = false)
 *          &#64;Convert(converter = UuidToStringConverter.class)
 *          private UUID uuid;
 *
 *          public SimpleEntity() {
 *              this.uuid = UUID.randomUUID();
 *          }
 *     }
 * </pre>
 *
 * @author Mislav Milicevic
 * @since 2019-09-09
 */
public class UuidToStringConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        return uuid == null ? null : uuid.toString();
    }

    @Override
    public UUID convertToEntityAttribute(String s) {
        return s == null ? null : UUID.fromString(s);
    }
}
