package com.punmac.footballmatchup.typeeditor;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class DateTimeTypeEditor extends PropertyEditorSupport {

    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    private static DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);

    @Override
    public void setAsText(String value) {
        if (value == null || "".equals(value)) {
            setValue(null);
        }
        setValue(formatter.parseDateTime(value));
    }

    @Override
    public String getAsText() {
        if (getValue() == null) {
            return new DateTime().toString(formatter);
        } else {
            return ((DateTime) getValue()).toString(formatter);
        }
    }
}
