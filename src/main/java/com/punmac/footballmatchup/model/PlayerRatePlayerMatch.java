package com.punmac.footballmatchup.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

/*
For rating player in each match
 */
public class PlayerRatePlayerMatch {
    private String id;
    @DBRef
    private Player player;
    @DBRef
    private PlayerMatch playerMatch;
    private int rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayerMatch getPlayerMatch() {
        return playerMatch;
    }

    public void setPlayerMatch(PlayerMatch playerMatch) {
        this.playerMatch = playerMatch;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
