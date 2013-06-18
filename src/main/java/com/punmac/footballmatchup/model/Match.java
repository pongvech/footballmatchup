package com.punmac.footballmatchup.model;

import org.joda.time.DateTime;

public class Match {

    private String id;
    private String name;
    private String detail;
    private String place;
    private DateTime dateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
