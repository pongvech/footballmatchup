package com.punmac.footballmatchup.core.dao;

import com.punmac.footballmatchup.core.model.PlayerRatePlayerMatch;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class PlayerRatePlayerMatchDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public PlayerRatePlayerMatch findById(String id) {
        return mongoTemplate.findOne(query(where("_id").is(id)), PlayerRatePlayerMatch.class);
    }

    // To find all rating for a player
    public List<PlayerRatePlayerMatch> findAllRatingByPlayerID(String playerId) {
        return mongoTemplate.find(query(where("player.$id").is(new ObjectId(playerId))), PlayerRatePlayerMatch.class);
    }

    // To find all rating for a player in a match
    public List<PlayerRatePlayerMatch> findAllRatingByPlayerIDAndMatch(String playerId, String matchId) {
        return mongoTemplate.find(query(where("player.playerMatch.$id").is(new ObjectId(playerId))
                .and("playerMatch.match.$id").is(new ObjectId(matchId))), PlayerRatePlayerMatch.class);
    }

    // To find who has been rated by this player in a match
    public List<PlayerRatePlayerMatch> findRatedByPlayerIdAndMatchId(String playerId, String matchId) {
        return mongoTemplate.find(query(where("player.$id").is(new ObjectId(playerId))
                .and("playerMatch.match.$id").is(new ObjectId(matchId))), PlayerRatePlayerMatch.class);
    }

    public List<PlayerRatePlayerMatch> findAll() {
        return mongoTemplate.findAll(PlayerRatePlayerMatch.class);
    }

    public void save(PlayerRatePlayerMatch playerRatePlayerMatch) {
        mongoTemplate.save(playerRatePlayerMatch);
    }

    public void deleteById(String id) {
        mongoTemplate.remove(query(where("_id").is(id)), PlayerRatePlayerMatch.class);
    }

}
