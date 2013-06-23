package com.punmac.footballmatchup.webapp.bean.display;

import com.punmac.footballmatchup.core.model.Match;
import com.punmac.footballmatchup.core.model.Player;
import com.punmac.footballmatchup.core.model.PlayerRating;

public class JoinedPlayerDisplay {

    private Player player;
    private Match match;
    private PlayerRating playerRating;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public PlayerRating getPlayerRating() {
        return playerRating;
    }

    public void setPlayerRating(PlayerRating playerRating) {
        this.playerRating = playerRating;
    }
}
