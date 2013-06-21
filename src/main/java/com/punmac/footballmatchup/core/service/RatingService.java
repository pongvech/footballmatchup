package com.punmac.footballmatchup.core.service;

import com.punmac.footballmatchup.core.dao.PlayerMatchDao;
import com.punmac.footballmatchup.core.dao.PlayerRatePlayerMatchDao;
import com.punmac.footballmatchup.core.model.Player;
import com.punmac.footballmatchup.core.model.PlayerRatePlayerMatch;
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
    private PlayerRatePlayerMatchDao playerRatePlayerMatchDao;

    @Autowired
    private PlayerMatchDao playerMatchDao;

    /*
    To calculate average rating for a player.
     */
    public double retrieveAverageRatingByPlayerId(String playerId) {
        List<PlayerRatePlayerMatch> overallRatingList = playerRatePlayerMatchDao.findAllRatingByPlayerID(playerId);
        double totalRating = 0;
        for (PlayerRatePlayerMatch matchRating : overallRatingList) {
            totalRating =+ matchRating.getRating();
        }
        return totalRating/overallRatingList.size();
    }

    /*
    To find who has been rated by this player in a match.
     */
    public List<Player> findWhoHasBeenRatedByPlayerIdInMatchId(String playerId, String matchId) {
        List<PlayerRatePlayerMatch> ratedByThisPlayerList = playerRatePlayerMatchDao.findRatedByPlayerIdAndMatchId(playerId, matchId);
        List<Player> ratedPlayerList = new ArrayList<>();
        for (PlayerRatePlayerMatch ratedByThisPlayer : ratedByThisPlayerList) {
            ratedPlayerList.add(ratedByThisPlayer.getPlayerMatch().getPlayer());
        }
        return ratedPlayerList;
    }

    // To check if the player already rated all players in the match.
    public boolean isPlayerFinishRating(String playerId, String matchId) {
        int ratedByThisPlayer = findWhoHasBeenRatedByPlayerIdInMatchId(playerId, matchId).size();
        int totalPlayerInMatch =  playerMatchDao.findAllPlayerInMatch(matchId).size();
        totalPlayerInMatch =- 1; // Minus himself...
        return ratedByThisPlayer == totalPlayerInMatch;
    }
}
