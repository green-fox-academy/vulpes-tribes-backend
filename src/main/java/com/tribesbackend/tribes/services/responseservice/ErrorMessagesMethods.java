package com.tribesbackend.tribes.services.responseservice;

import com.tribesbackend.tribes.models.TribesUser;
import org.springframework.stereotype.Service;

@Service
public class ErrorMessagesMethods {

    public static ErrorResponseModel usernameAlreadyTaken() {
        return new ErrorResponseModel("Username already taken, please choose anotherr one.");
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
    public ErrorResponseModel notSuchKingdom(String username){
        return new ErrorResponseModel("Not such kingdom: "+ username);
    }

    public ErrorResponseModel jsonUsernameNotProvided(){
        return new ErrorResponseModel("Wrong token provided");
    }
}
