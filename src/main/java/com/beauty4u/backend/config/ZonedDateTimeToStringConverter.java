package com.beauty4u.backend.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
@WritingConverter
public class ZonedDateTimeToStringConverter implements Converter<ZonedDateTime, String> {
    @Override
    public String convert(ZonedDateTime source) {
        return source.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
}


