package com.punmac.footballmatchup.dao;

import com.punmac.footballmatchup.model.PlayerMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlayerMatchDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(PlayerMatch playerMatch) {
        mongoTemplate.save(playerMatch);
    }

}
