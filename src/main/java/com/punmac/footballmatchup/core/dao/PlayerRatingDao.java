package com.punmac.footballmatchup.core.dao;

import com.punmac.footballmatchup.core.model.PlayerRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class PlayerRatingDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public PlayerRating findById(String id) {
        return mongoTemplate.findOne(query(where("_id").is(id)), PlayerRating.class);
    }

    /**
     * Find all rating of player.
     */
    public List<PlayerRating> findAllPlayerRating(String playerId) {
        return mongoTemplate.find(query(where("playerMatch.$player.$id").is(playerId)), PlayerRating.class);
    }

    /**
     * Find all rating of player in one match.
     */
    public List<PlayerRating> findAllPlayerRatingInMatch(String playerId, String matchId) {
        return mongoTemplate.find(query(where("playerMatch.$player.$id").is(playerId)
                .and("playerMatch.$match.$id").is(matchId)), PlayerRating.class);
    }

    public List<PlayerRating> findAll() {
        return mongoTemplate.findAll(PlayerRating.class);
    }

    public void save(PlayerRating playerRating) {
        mongoTemplate.save(playerRating);
    }

    public void deleteById(String id) {
        mongoTemplate.remove(query(where("_id").is(id)), PlayerRating.class);
    }
}
