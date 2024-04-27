package com.example.utils;

import java.util.HashMap;
import java.util.Map;

public class UserValidator {

    public Map<String, String> validateUserData(Map<String, String> userData) {
        Map<String, String> errors = new HashMap<>();

        if (userData.containsKey("id") & AppValidator.isValidId(userData.get("id"))) {
            errors.put("id", Constants.WRONG_ID_MSG);
        }

        if (userData.containsKey("name")){
            if (userData.get("name") != null & userData.get("name").isEmpty()){
                errors.put("name", Constants.INPUT_REQ_MSG);
            }
        }

        if (userData.containsKey("email") & AppValidator.isValidEmail(userData.get("email"))) {
            errors.put("email", Constants.WRONG_EMAIL_MSG);
        }

        return errors;
    }

}
