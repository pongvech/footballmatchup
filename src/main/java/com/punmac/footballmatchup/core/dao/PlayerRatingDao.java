package com.punmac.footballmatchup.core.dao;

import com.punmac.footballmatchup.core.model.PlayerRating;
import org.bson.types.ObjectId;
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
     * Find all rating of a player in every match.
     */
    public List<PlayerRating> findByPlayerId(String playerId) {
        return mongoTemplate.find(query(where("player.$id").is(new ObjectId(playerId))), PlayerRating.class);
    }

    /**
     * Find all rating of a player in one match.
     */
    public List<PlayerRating> findByPlayerIdAndMatchId(String playerId, String matchId) {
        return mongoTemplate.find(query(where("player.$id").is(new ObjectId(playerId))
                .and("match.$id").is(new ObjectId(matchId))), PlayerRating.class);
    }

    public List<PlayerRating> findByRaterIdAndMatchId(String raterId, String matchId) {
        return mongoTemplate.find(query(where("match.$id").is(new ObjectId(matchId))
                .and("rater.$id").is(new ObjectId(raterId))), PlayerRating.class);
    }

    /**
     * Find all rating of a player that is given by rater in one match.
     */
    public PlayerRating findByPlayerIdAndMatchIdAndRaterId(String playerId, String matchId, String raterId) {
        return mongoTemplate.findOne(query(where("player.$id").is(new ObjectId(playerId))
                .and("match.$id").is(new ObjectId(matchId))
                .and("rater.$id").is(new ObjectId(raterId))), PlayerRating.class);
    }

    /**
     * Find all rating of every player in a match.
     */
    public List<PlayerRating> findByMatchId(String matchId) {
        return mongoTemplate.find(query(where("match.$id").is(new ObjectId(matchId))), PlayerRating.class);
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
