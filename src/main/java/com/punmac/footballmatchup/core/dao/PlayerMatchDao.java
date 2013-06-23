package com.punmac.footballmatchup.core.dao;

import com.punmac.footballmatchup.core.model.PlayerMatch;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class PlayerMatchDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public PlayerMatch findById(String id) {
        return mongoTemplate.findOne(query(where("_id").is(id)), PlayerMatch.class);
    }

    public PlayerMatch findByPlayerIdAndMatchId(String playerId, String matchId) {
        return mongoTemplate.findOne(query(where("player.$id").is(new ObjectId(playerId))
                .and("match.$id").is(new ObjectId(matchId))), PlayerMatch.class);
    }

    public List<PlayerMatch> findAllPlayerInMatch(String matchId) {
        return mongoTemplate.find(query(where("match.$id").is(new ObjectId(matchId))), PlayerMatch.class);
    }

    public List<PlayerMatch> findAll() {
        return mongoTemplate.findAll(PlayerMatch.class);
    }

    public void save(PlayerMatch playerMatch) {
        mongoTemplate.save(playerMatch);
    }

    public void deleteById(String id) {
        mongoTemplate.remove(query(where("_id").is(id)), PlayerMatch.class);
    }
}
