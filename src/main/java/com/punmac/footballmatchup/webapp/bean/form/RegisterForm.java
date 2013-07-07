package com.punmac.footballmatchup.webapp.bean.form;

import com.punmac.footballmatchup.core.model.Player;

public class RegisterForm extends Player {

    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
