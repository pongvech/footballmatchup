package com.punmac.footballmatchup.typeeditor;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class DateTimeTypeEditor extends PropertyEditorSupport {

    // TODO Move DATE_FORMAT to properties
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    private static DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);

    @Override
    public void setAsText(String value) {
        if ("".equals(value) || value == null) {
            setValue(null);
            return;
        }
        setValue(formatter.parseDateTime(value));
    }

    @Override
    public String getAsText() {
        if ("".equals(getValue()) || getValue() == null) {
            return null;
        } else {
            return ((DateTime) getValue()).toString(formatter);
        }
    }
}
