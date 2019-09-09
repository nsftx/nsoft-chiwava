package com.nsoft.chiwava.core.persistence.converters;

import javax.persistence.AttributeConverter;
import java.util.UUID;

/**
 * Converts UUID properties inside entities to Strings
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
