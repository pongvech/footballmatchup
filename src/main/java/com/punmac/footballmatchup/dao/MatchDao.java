package com.punmac.footballmatchup.dao;

import com.punmac.footballmatchup.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Match> findAll() {
        return mongoTemplate.findAll(Match.class);
    }

    public void save(Match match) {
        mongoTemplate.save(match);
    }
}
