package com.nsoft.chiwava.core.commons.unit.time;

import com.nsoft.chiwava.core.commons.time.DateTimeFormats;
import org.junit.Test;

import java.time.LocalDateTime;

public class DateTimeFormatsTest {

    @Test
    public void shouldFormatDefaultUTC() {
        LocalDateTime localDateTime = LocalDateTime.now();

        localDateTime.format(DateTimeFormats.UTC_DEFAULT);
    }

    @Test
    public void shouldFormatExtendedUTC() {
        LocalDateTime localDateTime = LocalDateTime.now();

        localDateTime.format(DateTimeFormats.UTC_ZULU);
    }

}
