package com.punmac.footballmatchup.webapp.validator;

import com.punmac.footballmatchup.core.dao.PlayerDao;
import com.punmac.footballmatchup.core.model.Player;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegisterValidator implements Validator {

    /**
     * Allowed a-z, A-Z, 0-9, _ and - for username.
     */
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]*$";
    private static final int USERNAME_MIN_LENGTH = 3;
    private static final int USERNAME_MAX_LENGTH = 12;

    @Autowired
    private PlayerDao playerDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return Player.class.equals(clazz);
    }

    /**
     * email is required, must be valid format and unique.
     * username is required, must be unique, valid format and length is between 3 - 12 char.
     * password is required.
     */
    @Override
    public void validate(Object target, Errors errors) {
        Player player = (Player) target;

        if("".equals(player.getEmail())) {
            errors.rejectValue("email", null, "Email is required");
        }
        if(!errors.hasFieldErrors("email") && !EmailValidator.getInstance().isValid(player.getEmail())) {
            errors.rejectValue("email", null, "Email is invalid");
        }
        if(!errors.hasFieldErrors("email") && playerDao.findByEmail(player.getEmail()) != null) {
            errors.rejectValue("email", null, "Email already exist");
        }

        if("".equals(player.getUsername())) {
            errors.rejectValue("username", null, "Username is required");
        }
        int usernameLength = player.getUsername().length();
        if(!errors.hasFieldErrors("username") && usernameLength < USERNAME_MIN_LENGTH) {
            errors.rejectValue("username", null, "Username must not shorter than 3 characters");
        }
        if(!errors.hasFieldErrors("username") && usernameLength > USERNAME_MAX_LENGTH) {
            errors.rejectValue("username", null, "Username must not longer than 12 characters");
        }
        if(!errors.hasFieldErrors("username") && !isUsernameValid(player.getUsername())) {
            errors.rejectValue("username", null, "Username allow a-z, A-Z, 0-9, Underscore and Hypen only");
        }
        if(!errors.hasFieldErrors("username") && playerDao.findByUsername(player.getUsername()) != null) {
            errors.rejectValue("username", null, "Username already exist");
        }

        if("".equals(player.getPassword())) {
            errors.rejectValue("password", null, "Password is required");
        }
    }

    private boolean isUsernameValid(String username) {
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
}
