package com.punmac.footballmatchup.core.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * We can not have PlayerMatch in here because
 * mongoTemplate.find(query(where("playerMatch.$match.$id").is(new ObjectId(matchId))), PlayerRating.class);
 * does not work.
 * "@DBRef" can not link to other collection more than 1 level.
 */
public class PlayerRating {

    private String id;
    private int rating;
    @DBRef
    private Player rater; // Player who give rating.
    @DBRef
    private Player player; // Player who is estimated by rater.
    @DBRef
    private Match match;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Player getRater() {
        return rater;
    }

    public void setRater(Player rater) {
        this.rater = rater;
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
        return "PlayerRating{" +
                "id='" + id + '\'' +
                ", rating=" + rating +
                ", rater=" + rater +
                ", player=" + player +
                ", match=" + match +
                '}';
    }
}
