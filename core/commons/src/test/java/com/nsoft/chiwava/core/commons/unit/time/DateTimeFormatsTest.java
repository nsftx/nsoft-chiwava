package com.nsoft.chiwava.core.commons.unit.time;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.nsoft.chiwava.core.commons.time.DateTimeFormats;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DateTimeFormatsTest {

    @Test
    public void shouldFormatDefaultUTC() {
        LocalDateTime localDateTime = LocalDateTime.now();

        assertNotNull(localDateTime.format(DateTimeFormats.UTC_DEFAULT));
    }

    @Test
    public void shouldFormatExtendedUTC() {
        LocalDateTime localDateTime = LocalDateTime.now();

        assertNotNull(localDateTime.format(DateTimeFormats.UTC_ZULU));
    }

}
