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
