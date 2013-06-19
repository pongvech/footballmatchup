package com.punmac.footballmatchup.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FootballMatchUpProperties {

    @Value(value = "${footballmatchup.format.datetime}")
    private String formatDateTime;

    public String getFormatDateTime() {
        return formatDateTime;
    }

    public void setFormatDateTime(String formatDateTime) {
        this.formatDateTime = formatDateTime;
    }
}
