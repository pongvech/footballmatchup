package com.punmac.footballmatchup.validator;

import com.punmac.footballmatchup.model.Match;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SaveMatchValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Match.class.equals(clazz);
    }

    /**
     * name is reuqired.
     * place is reqired.
     * dateTime is required and must be valid format.
     */
    @Override
    public void validate(Object target, Errors errors) {
        Match match = (Match) target;

        if("".equals(match.getName())) {
            errors.rejectValue("name", null, "Name is required");
        }

        if("".equals(match.getPlace())) {
            errors.rejectValue("place", null, "Place is required");
        }

        if(match.getDateTime() == null) {
            errors.rejectValue("dateTime", null, "Date Time is required");
        }
    }
}
