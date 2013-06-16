package com.punmac.footballmatchup.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class PlayerMatch {

    private String id;
    private int team;
    @DBRef
    private Player player;
    @DBRef
    private Match match;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "PlayerMatch{" +
                "id='" + id + '\'' +
                ", team=" + team +
                ", player=" + player +
                ", match=" + match +
                '}';
    }
}
