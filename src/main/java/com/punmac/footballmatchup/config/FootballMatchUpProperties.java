package com.punmac.footballmatchup.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FootballMatchUpProperties {

    @Value(value = "${footballmatchup.format.datetime}")
    private String formatDateTime;

    @Value(value = "${footballmatchup.format.datetime.view}")
    private String formatDateTimeView;

    @Value(value = "${footballmatchup.pagination.loadmore.limit}")
    private int paginationLoadMoreLimit;

    public String getFormatDateTime() {
        return formatDateTime;
    }

    public void setFormatDateTime(String formatDateTime) {
        this.formatDateTime = formatDateTime;
    }

    public String getFormatDateTimeView() {
        return formatDateTimeView;
    }

    public void setFormatDateTimeView(String formatDateTimeView) {
        this.formatDateTimeView = formatDateTimeView;
    }

    public int getPaginationLoadMoreLimit() {
        return paginationLoadMoreLimit;
    }

    public void setPaginationLoadMoreLimit(int paginationLoadMoreLimit) {
        this.paginationLoadMoreLimit = paginationLoadMoreLimit;
    }
}
