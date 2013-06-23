package com.punmac.footballmatchup.core.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class PlayerRating {

    private String id;
    private int score;
    @DBRef
    private Player rater; // Player who give rating.
    @DBRef
    private PlayerMatch playerMatch;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player getRater() {
        return rater;
    }

    public void setRater(Player rater) {
        this.rater = rater;
    }

    public PlayerMatch getPlayerMatch() {
        return playerMatch;
    }

    public void setPlayerMatch(PlayerMatch playerMatch) {
        this.playerMatch = playerMatch;
    }

    @Override
    public String toString() {
        return "PlayerRating{" +
                "id='" + id + '\'' +
                ", score=" + score +
                ", rater=" + rater +
                ", playerMatch=" + playerMatch +
                '}';
    }
}
