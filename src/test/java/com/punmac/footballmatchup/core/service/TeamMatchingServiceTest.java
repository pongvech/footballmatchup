package com.punmac.footballmatchup.core.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamMatchingServiceTest {

    private static final Logger log = LoggerFactory.getLogger(TeamMatchingServiceTest.class);

    @Test
    public void testMatchUpAlgorithm() {
        List<Double> playerRatingList = new ArrayList<>();
        playerRatingList.add(4.3);
        playerRatingList.add(4.1);
        playerRatingList.add(3.0);
        playerRatingList.add(3.0);
        playerRatingList.add(2.9);
        playerRatingList.add(2.8);
        playerRatingList.add(2.4);
        playerRatingList.add(1.0);

        int playerSize = playerRatingList.size();
        // Store index of playerRatingList.
        List<Integer> teamAIndexList = new ArrayList<>();
        List<Integer> teamBIndexList = new ArrayList<>();
        if(playerSize % 2 == 0) {
            for(int i = 0; i < playerSize / 2; i++) {
                int partnerIndex = calculatePartner(i, playerSize);
                // log.debug("{} : {}", i, partnerIndex);
                if(i % 2 == 0) {
                    // log.debug("Team A");
                    teamAIndexList.add(i);
                    teamAIndexList.add(partnerIndex);
                } else {
                    // log.debug("Team B");
                    teamBIndexList.add(i);
                    teamBIndexList.add(partnerIndex);
                }
            }
        }

        Map<Integer, Double> teamAMap = new HashMap<>();
        Map<Integer, Double> teamBMap = new HashMap<>();
        for(int i = 0; i < teamAIndexList.size(); i++) {
            teamAMap.put(teamAIndexList.get(i), playerRatingList.get(teamAIndexList.get(i)));
            teamBMap.put(teamBIndexList.get(i), playerRatingList.get(teamBIndexList.get(i)));
        }

        double teamASum = 0.0;
        double teamBSum = 0.0;
        log.debug("========== Team A ==========");
        for (Map.Entry<Integer, Double> entry : teamAMap.entrySet()) {
            log.debug("Index = {}, Rating = {}", entry.getKey(), entry.getValue());
            teamASum += entry.getValue();
        }
        log.debug("========== Team B ==========");
        for (Map.Entry<Integer, Double> entry : teamBMap.entrySet()) {
            log.debug("Index = {}, Rating = {}", entry.getKey(), entry.getValue());
            teamBSum += entry.getValue();
        }
        log.debug("Team A Sum = {}, Team B Sum = {}", teamASum, teamBSum);

        // Exchange player who have smallest rating.
        if(teamASum < teamBSum) {
            // Find player who have smallest rating of Team A.
//            int playerIndexTemp = 0; // Index of player who have smallest rating.
//            for (Map.Entry<Integer, Double> entry : teamAMap.entrySet()) {
//                if(playerIndexTemp < entry.getKey()) {
//
//                }
//            }
        }
    }

    /**
     * Find player partner.
     * For ex, If sort player by avg rating desc.
     * Player 0 's partner is 7
     * Player 1 's partner is 6
     * Player 2 's partner is 5
     * Player 3 's partner is 4
     */
    private int calculatePartner(int index, int size) {
        int partner = index + size - (1 + (index * 2));
        // log.debug("Index = {}, Partner = {}", index, partner);
        return partner;
    }
}
