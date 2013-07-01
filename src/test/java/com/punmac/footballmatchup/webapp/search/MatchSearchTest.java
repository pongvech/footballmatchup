package com.punmac.footballmatchup.webapp.search;

import com.punmac.footballmatchup.config.FootballMatchUpProperties;
import com.punmac.footballmatchup.core.dao.MatchDao;
import com.punmac.footballmatchup.core.model.Match;
import com.punmac.footballmatchup.webapp.bean.form.search.MatchSearchForm;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:footballmatchup-context-test.xml", "classpath:footballmatchup-db-test.xml"})
public class MatchSearchTest {

    @Autowired
    @InjectMocks
    private MatchSearch matchSearch;

    @Autowired
    private MatchDao matchDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Mock
    private FootballMatchUpProperties footballMatchUpProperties;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(footballMatchUpProperties.getPaginationLoadMoreLimit()).thenReturn(2);
    }

    @After
    public void tearDown() {
        mongoTemplate.dropCollection(Match.class);
    }

    @Test
    public void testSearchMatch() {
        // Add 7 matchs.
        Match match1 = new Match();
        match1.setName("Match1");
        matchDao.save(match1);
        Match match2 = new Match();
        match2.setName("Match2");
        matchDao.save(match2);
        Match match3 = new Match();
        match3.setName("Match3");
        matchDao.save(match3);
        Match match4 = new Match();
        match4.setName("Match4");
        matchDao.save(match4);
        Match match5 = new Match();
        match5.setName("Match5");
        matchDao.save(match5);
        Assert.assertEquals(5, matchDao.findAll().size());

        // Assert page 1
        MatchSearchForm matchSearchForm = new MatchSearchForm();
        matchSearchForm.setStart(0);
        List<Match> matchList = matchSearch.searchMatch(matchSearchForm);
        Assert.assertEquals(2, matchList.size());
        Assert.assertEquals(match5.getName(), matchList.get(0).getName());
        Assert.assertEquals(match4.getName(), matchList.get(1).getName());

        // Assert page 2
        matchSearchForm = new MatchSearchForm();
        matchSearchForm.setStart(2);
        matchList = matchSearch.searchMatch(matchSearchForm);
        Assert.assertEquals(2, matchList.size());
        Assert.assertEquals(match3.getName(), matchList.get(0).getName());
        Assert.assertEquals(match2.getName(), matchList.get(1).getName());

        // Assert page 3
        matchSearchForm = new MatchSearchForm();
        matchSearchForm.setStart(4);
        matchList = matchSearch.searchMatch(matchSearchForm);
        Assert.assertEquals(1, matchList.size());
        Assert.assertEquals(match1.getName(), matchList.get(0).getName());
    }
}
