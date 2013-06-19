package com.punmac.footballmatchup.typeeditor;

import com.punmac.footballmatchup.config.FootballMatchUpProperties;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class DateTimeTypeEditor extends PropertyEditorSupport {

    @Autowired
    private FootballMatchUpProperties footballMatchUpProperties;

    // !isValidDateTime(value) should not be here.
    @Override
    public void setAsText(String value) {
        if ("".equals(value) || !isValidDateTime(value) || value == null) {
            setValue(null);
            return;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(footballMatchUpProperties.getFormatDateTime());
        setValue(dateTimeFormatter.parseDateTime(value));
    }

    @Override
    public String getAsText() {
        if (getValue() == null) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(footballMatchUpProperties.getFormatDateTime());
        return ((DateTime) getValue()).toString(dateTimeFormatter);
    }

    // TODO move isValidDateTime() to validator class
    /**
     * Check whether dateTime is valid format or not.
     * @param value dateTime
     * @return bool true if dateTime is valid format, else false.
     */
    private boolean isValidDateTime(String value) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(footballMatchUpProperties.getFormatDateTime());
        try {
            dateTimeFormatter.parseDateTime(value);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
