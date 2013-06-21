package com.punmac.footballmatchup.webapp.validator;

import com.punmac.footballmatchup.webapp.bean.form.LoginForm;
import com.punmac.footballmatchup.core.dao.PlayerDao;
import com.punmac.footballmatchup.core.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

    @Autowired
    private PlayerDao playerDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginForm.class.equals(clazz);
    }

    /**
     * usernameOrPassword is required and must be exist.
     * password is required and must be correct.
     */
    @Override
    public void validate(Object target, Errors errors) {
        LoginForm loginForm = (LoginForm) target;

        if("".equals(loginForm.getEmailOrUsername())) {
            errors.rejectValue("emailOrUsername", null, "Email or Username is required");
        }
        if(!errors.hasFieldErrors("emailOrUsername") &&
                playerDao.findByEmailOrUsername(loginForm.getEmailOrUsername()) == null) {
            errors.rejectValue("emailOrUsername", null, "Email or Username does not exist");
        }

        if("".equals(loginForm.getPassword())) {
            errors.rejectValue("password", null, "Password is required");
        }
        if(!errors.hasFieldErrors("emailOrUsername") && !errors.hasFieldErrors("password")) {
            Player player = playerDao.loginWithEmailOrUsername(loginForm.getEmailOrUsername(), loginForm.getPassword());
            /**
             * Set player. When login success, use field player to create cookie or session.
             */
            loginForm.setPlayer(player);
            if(player == null) {
                errors.rejectValue("emailOrUsername", null, "Password is incorrect");
            }
        }
    }
}
