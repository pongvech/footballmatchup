package com.punmac.footballmatchup.bean.form;

import com.punmac.footballmatchup.model.Player;

public class LoginForm {

    private String emailOrUsername;
    private String password;
    private boolean rememberMe;
    /**
     * Require player in here, when LoginValidator does not have any error it will set player and 
     * Controller can use player to create cookie or session. Controller does not need to find player from db again.
     */
    private Player player;

    public String getEmailOrUsername() {
        return emailOrUsername;
    }

    public void setEmailOrUsername(String emailOrUsername) {
        this.emailOrUsername = emailOrUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "emailOrUsername='" + emailOrUsername + '\'' +
                ", password='" + password + '\'' +
                ", rememberMe=" + rememberMe +
                ", player=" + player +
                '}';
    }
}
