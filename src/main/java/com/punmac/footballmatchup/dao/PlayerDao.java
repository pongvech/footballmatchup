package com.punmac.footballmatchup.dao;

import com.punmac.footballmatchup.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
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

    public Player findByUsername(String username) {
        return mongoTemplate.findOne(query(where("username").is(username)), Player.class);
    }

    public Player findByEmailOrUsername(String emailOrUsername) {
        BasicQuery query = new BasicQuery("{$or:[{email:'" + emailOrUsername + "'}, " +
                "{username:'" + emailOrUsername + "'}]}");
        Player player = mongoTemplate.findOne(query, Player.class);
        return player;
    }

    public Player loginWithEmailOrUsername(String emailOrUsername, String password) {
        BasicQuery query = new BasicQuery("{$or:[{email:'" + emailOrUsername + "'}, " +
                "{username:'" + emailOrUsername + "'}], password:'" + password + "'}");
        Player player = mongoTemplate.findOne(query, Player.class);
        return player;
    }

    public List<Player> findAll() {
        return mongoTemplate.findAll(Player.class);
    }

    public void save(Player player) {
        mongoTemplate.save(player);
    }

    public void deleteById(String id) {
        mongoTemplate.remove(query(where("_id").is(id)), Player.class);
    }
}
