package com.punmac.footballmatchup.validator;

import com.punmac.footballmatchup.dao.PlayerDao;
import com.punmac.footballmatchup.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegisterValidator implements Validator {

    @Autowired
    private PlayerDao playerDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return Player.class.equals(aClass);
    }

    /**
     * email is required and unique.
     * username is required and unique,
     * password is required.
     */
    @Override
    public void validate(Object o, Errors errors) {
        Player player = (Player) o;

        if("".equals(player.getEmail())) {
            errors.rejectValue("email", null, "Email is required");
        }
        if(!errors.hasFieldErrors("email") && playerDao.findByEmail(player.getEmail()) != null) {
            errors.rejectValue("email", null, "Email already exist");
        }

        if("".equals(player.getUsername())) {
            errors.rejectValue("username", null, "Username is reqired");
        }
        if(!errors.hasFieldErrors("username") && playerDao.findByUsername(player.getUsername()) != null) {
            errors.rejectValue("username", null, "Username already exist");
        }

        if("".equals(player.getPassword())) {
            errors.rejectValue("password", null, "Password is reqired");
        }
    }
}
