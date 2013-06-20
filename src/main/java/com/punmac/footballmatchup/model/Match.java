package com.punmac.footballmatchup.model;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Match {

    private String id;
    private String name;
    private String detail;
    private String place;
    private DateTime playTime;
    private DateTime createdTime;
    @DBRef
    private Player creator;

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

    public DateTime getPlayTime() {
        return playTime;
    }

    public void setPlayTime(DateTime playTime) {
        this.playTime = playTime;
    }

    public DateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(DateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Player getCreator() {
        return creator;
    }

    public void setCreator(Player creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", place='" + place + '\'' +
                ", playTime=" + playTime +
                ", createdTime=" + createdTime +
                ", creator=" + creator +
                '}';
    }
}
