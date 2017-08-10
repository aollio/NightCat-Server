package com.framework.formatter;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Locale;

@Component
public class TimestampFormatter implements org.springframework.format.Formatter<Timestamp> {


    @Override
    public Timestamp parse(String text, Locale locale) throws ParseException {
        return new Timestamp(Long.parseLong(text));
    }

    @Override
    public String print(Timestamp object, Locale locale) {
        return object == null ? "null" : object.toString();
    }
}
