package com.tribesbackend.tribes.tribesuser.service.errorservice;

import com.tribesbackend.tribes.tribesuser.model.TribesUser;

public class ErrorMessagesMethods {

    public static ErrorResponseModel usernameAlreadyTaken() {
        return new ErrorResponseModel("Username already taken, please choose another one.");
    }

    public static ErrorResponseModel jsonFieldIsEmpty(TribesUser user) {
        return new ErrorResponseModel("Missing parameter(s): " + getErrorParameter(user));
    }

    public static String getErrorParameter(TribesUser user) {
        StringBuffer concatBuffer = new StringBuffer();
        if (user.getUsername().isEmpty() || user.getUsername() == null) {
            concatBuffer.append("username");
        }
        if (user.getPassword().isEmpty() || user.getPassword() == null) {
            if (concatBuffer.length() != 0) {

                concatBuffer.append(", password");
            } else concatBuffer.append("password");
        }
        return concatBuffer.toString();
    }

    public static ErrorResponseModel wrongPassword() {
        return new ErrorResponseModel("Wrong password!");
    }

    public static ErrorResponseModel notSuchUser(String username) {
        return new ErrorResponseModel("Not such user: " + username);
    }
}
