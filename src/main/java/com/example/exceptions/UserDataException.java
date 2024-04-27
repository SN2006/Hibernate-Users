package com.example.exceptions;

import java.util.Map;

public class UserDataException extends Exception{

    public UserDataException(String message) {
        super(message);
    }

    public String getErrors(Map<String, String> errors){
        StringBuilder builder = new StringBuilder("\n");
        errors.forEach((field, error) ->
            builder.append(">> ").append(field).append(": ").append(error).append("\n")
        );
        return builder.toString();
    }
}
