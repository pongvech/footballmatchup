package com.punmac.footballmatchup.webapp.search;

import com.punmac.footballmatchup.config.FootballMatchUpProperties;
import com.punmac.footballmatchup.core.model.Match;
import com.punmac.footballmatchup.webapp.bean.form.MatchSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class MatchSearch {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private FootballMatchUpProperties footballMatchUpProperties;

    /**
     * Search Match.
     */
    public List<Match> searchMatch(MatchSearchForm matchSearchForm) {
        Query query = new Query();
        if(!"".equals(matchSearchForm.getName()) && matchSearchForm.getName() != null) {
            query.addCriteria(where("name").is(matchSearchForm.getName()));
        }
        query.with(new Sort(Sort.Direction.DESC, "createdTime"));
        // TODO http://docs.mongodb.org/manual/reference/method/cursor.skip/
        // TODO As offset increases, cursor.skip() will become slower and more CPU intensive.
        query.skip(matchSearchForm.getStart());
        query.limit(footballMatchUpProperties.getPaginationLoadMoreLimit());
        return mongoTemplate.find(query, Match.class);
    }
}
