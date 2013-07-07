package com.punmac.footballmatchup.webapp.bean.display;

import com.punmac.footballmatchup.core.model.PlayerMatch;

import java.util.List;

public class PlayerMatchListDisplay {

    private List<PlayerMatch> playerMatchList;
    private String teamAPercentage;
    private String teamBPercentage;

    public List<PlayerMatch> getPlayerMatchList() {
        return playerMatchList;
    }

    public void setPlayerMatchList(List<PlayerMatch> playerMatchList) {
        this.playerMatchList = playerMatchList;
    }

    public String getTeamAPercentage() {
        return teamAPercentage;
    }

    public void setTeamAPercentage(String teamAPercentage) {
        this.teamAPercentage = teamAPercentage;
    }

    public String getTeamBPercentage() {
        return teamBPercentage;
    }

    public void setTeamBPercentage(String teamBPercentage) {
        this.teamBPercentage = teamBPercentage;
    }
}
