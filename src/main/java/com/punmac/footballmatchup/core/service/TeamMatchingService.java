package com.punmac.footballmatchup.core.service;

import com.punmac.footballmatchup.core.dao.MatchDao;
import com.punmac.footballmatchup.core.dao.PlayerMatchDao;
import com.punmac.footballmatchup.core.dao.PlayerRatingDao;
import com.punmac.footballmatchup.core.model.Match;
import com.punmac.footballmatchup.core.model.PlayerMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TeamMatchingService {
    private static final Logger log = LoggerFactory.getLogger(TeamMatchingService.class);

    @Autowired
    private PlayerMatchDao playerMatchDao;

    @Autowired
    private MatchDao matchDao;

    @Autowired
    private PlayerRatingDao playerRatingDao;

    @Autowired
    private RatingService ratingService;

    public boolean matchUp(String matchId) {
        Match match = matchDao.findById(matchId);
        if (match == null) {
            log.error("MatchID {} not found", matchId);
            return false;
        }
        List<PlayerRating> playerRatingList = new ArrayList<>();
        List<PlayerMatch> playerMatchList = playerMatchDao.findAllPlayerInMatch(match.getId());
        for (PlayerMatch playerMatch : playerMatchList) {
            PlayerRating playerRating = new PlayerRating();
            playerRating.setPlayerMatch(playerMatch);
            playerRating.setAverageRating(ratingService.retrieveAverageRatingByPlayerId(playerMatch.getPlayer().getId()));
            playerRatingList.add(playerRating);
        }
        Collections.sort(playerRatingList, new RatingComparator());
        boolean teamA = true;
        for (PlayerRating playerRating : playerRatingList) {
            if (teamA) {
                playerRating.getPlayerMatch().setTeam(1);
                playerMatchDao.save(playerRating.getPlayerMatch());
                teamA = false;
            } else {
                playerRating.getPlayerMatch().setTeam(2);
                playerMatchDao.save(playerRating.getPlayerMatch());
                teamA = true;
            }

        }
        return true;
    }

    private class RatingComparator implements Comparator<PlayerRating> {
        @Override
        public int compare(PlayerRating rating1, PlayerRating rating2) {
            if (rating1.getAverageRating() > rating2.getAverageRating()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private class PlayerRating {
        private PlayerMatch playerMatch;
        private double averageRating;

        private PlayerMatch getPlayerMatch() {
            return playerMatch;
        }

        private void setPlayerMatch(PlayerMatch playerMatch) {
            this.playerMatch = playerMatch;
        }

        private double getAverageRating() {
            return averageRating;
        }

        private void setAverageRating(double averageRating) {
            this.averageRating = averageRating;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("PlayerRating{");
            sb.append("playerMatch=").append(playerMatch);
            sb.append(", averageRating=").append(averageRating);
            sb.append('}');
            return sb.toString();
        }
    }
}
