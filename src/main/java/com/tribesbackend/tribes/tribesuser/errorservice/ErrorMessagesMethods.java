package com.tribesbackend.tribes.tribesuser.errorservice;

import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessagesMethods {

    public ErrorResponseModel usernameAlreadyTaken() {
        ErrorResponseModel toReturn = new ErrorResponseModel();
        toReturn.setStatus("error");
        toReturn.setErrorMessage("Username already taken, please choose another one.");
        return toReturn;
    }

    public ErrorResponseModel jsonFieldIsEmpty(TribesUser user) {
        ErrorResponseModel toReturn = new ErrorResponseModel();
        toReturn.setStatus("error");
        toReturn.setErrorMessage("Missing parameter(s): " + getErrorParameter(user));
        return toReturn;
    }

    private String getErrorParameter(TribesUser user) {
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
}
