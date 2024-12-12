package com.beauty4u.backend.config.zoneDateTime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
@ReadingConverter
public class StringToZonedDateTimeConverter implements Converter<String, ZonedDateTime> {
    @Override
    public ZonedDateTime convert(String source) {
        return ZonedDateTime.parse(source, DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
}
