package com.punmac.footballmatchup.webapp.bean.display;

import com.punmac.footballmatchup.core.model.PlayerMatch;

public class PlayerMatchDisplay {

    private PlayerMatch playerMatch;
    private String teamAPercentage;
    private String teamBPercentage;

    public PlayerMatch getPlayerMatch() {
        return playerMatch;
    }

    public void setPlayerMatch(PlayerMatch playerMatch) {
        this.playerMatch = playerMatch;
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
