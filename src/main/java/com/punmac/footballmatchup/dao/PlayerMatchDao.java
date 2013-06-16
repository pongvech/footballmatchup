package com.punmac.footballmatchup.dao;

import com.punmac.footballmatchup.model.PlayerMatch;
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
