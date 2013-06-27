package com.punmac.footballmatchup.core.model;

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
    private int teamAScore = 0;
    private int teamBScore = 0;

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

    public int getTeamAScore() {
        return teamAScore;
    }

    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }

    public int getTeamBScore() {
        return teamBScore;
    }

    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Match{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", detail='").append(detail).append('\'');
        sb.append(", place='").append(place).append('\'');
        sb.append(", playTime=").append(playTime);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", creator=").append(creator);
        sb.append(", teamAScore=").append(teamAScore);
        sb.append(", teamBScore=").append(teamBScore);
        sb.append('}');
        return sb.toString();
    }
}
