package com.punmac.footballmatchup.core.service;

import com.punmac.footballmatchup.core.dao.PlayerMatchDao;
import com.punmac.footballmatchup.core.dao.PlayerRatingDao;
import com.punmac.footballmatchup.core.model.Player;
import com.punmac.footballmatchup.core.model.PlayerRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingService {
    private static final Logger log = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    private PlayerRatingDao playerRatingDao;

    @Autowired
    private PlayerMatchDao playerMatchDao;

    /*
    To calculate average rating for a player.
     */
    public double retrieveAverageRatingByPlayerId(String playerId) {
        List<PlayerRating> overallRatingList = playerRatingDao.findByPlayerId(playerId);
        double totalRating = 0;
        for (PlayerRating matchRating : overallRatingList) {
            //log.debug("Player {} Match {} Rating {} ", matchRating.getPlayer().getEmail(), matchRating.getMatch().getName(), matchRating.getRating());
            totalRating += matchRating.getRating();
        }
        if (overallRatingList.size() == 0) {
            return 0;
        }
        double averagedRating = totalRating/overallRatingList.size();
        log.debug("Averaged for {} : {} ", overallRatingList.get(0).getPlayer().getEmail() ,averagedRating);
        return averagedRating;
    }

    /*
    To find who has been rated by this player in a match.
     */
    public List<Player> findWhoHasBeenRatedByPlayerIdInMatchId(String playerId, String matchId) {
        List<PlayerRating> ratedByThisPlayerList = playerRatingDao.findByPlayerIdAndMatchId(playerId, matchId);
        List<Player> ratedPlayerList = new ArrayList<>();
        for (PlayerRating ratedByThisPlayer : ratedByThisPlayerList) {
            ratedPlayerList.add(ratedByThisPlayer.getPlayer());
        }
        return ratedPlayerList;
    }

    // To check if the player already rated all players in the match.
    public boolean isPlayerFinishRating(String playerId, String matchId) {
        int ratedByThisPlayer = findWhoHasBeenRatedByPlayerIdInMatchId(playerId, matchId).size();
        int totalPlayerInMatch = playerMatchDao.findAllPlayerInMatch(matchId).size() - 1; // Minus himself
        return ratedByThisPlayer == totalPlayerInMatch;
    }
}
