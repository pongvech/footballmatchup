package com.punmac.footballmatchup.dao;

import com.punmac.footballmatchup.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class PlayerDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Player findById(String id) {
        return mongoTemplate.findOne(query(where("_id").is(id)), Player.class);
    }

    public Player findByEmail(String email) {
        return mongoTemplate.findOne(query(where("email").is(email)), Player.class);
    }

    public Player loginWithEmail(String email, String password) {
        return mongoTemplate.findOne(query(where("email").is(email).and("password").is(password)), Player.class);
    }

    public List<Player> findAll() {
        return mongoTemplate.findAll(Player.class);
    }

    public void save(Player player) {
        mongoTemplate.save(player);
    }

    public void delete(String id) {
        mongoTemplate.remove(query(where("_id").is(id)), Player.class);
    }
}
