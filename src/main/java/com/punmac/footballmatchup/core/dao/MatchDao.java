package com.punmac.footballmatchup.core.dao;

import com.punmac.footballmatchup.core.model.Match;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class MatchDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Match findById(String id) {
        return mongoTemplate.findOne(query(where("_id").is(id)), Match.class);
    }

    public List<Match> findAll() {
        return mongoTemplate.findAll(Match.class);
    }

    public void save(Match match) {
        // Set createdTime if add new match.
        if(match.getId() == null) {
            match.setCreatedTime(DateTime.now());
        }
        mongoTemplate.save(match);
    }

    public void deleteById(String id) {
        mongoTemplate.remove(query(where("_id").is(id)), Match.class);
    }
}
