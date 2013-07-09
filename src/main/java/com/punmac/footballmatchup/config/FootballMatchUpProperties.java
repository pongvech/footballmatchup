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

    @Value(value = "${footballmatchup.fb.appid}")
    private String fbAppId;

    @Value(value = "${footballmatchup.fb.appsecret}")
    private String fbAppSecret;

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

    public String getFbAppId() {
        return fbAppId;
    }

    public void setFbAppId(String fbAppId) {
        this.fbAppId = fbAppId;
    }

    public String getFbAppSecret() {
        return fbAppSecret;
    }

    public void setFbAppSecret(String fbAppSecret) {
        this.fbAppSecret = fbAppSecret;
    }
}
