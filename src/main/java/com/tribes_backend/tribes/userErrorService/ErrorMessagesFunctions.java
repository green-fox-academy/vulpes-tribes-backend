package com.tribes_backend.tribes.userErrorService;

import com.tribes_backend.tribes.userModel.TribesUser;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessagesFunctions {


    public ErrorResponseModel usernameAlreadyTaken(){
        ErrorResponseModel toReturn = new ErrorResponseModel();
        toReturn.setStatus("error");
        toReturn.setErrorMessage("Username already taken, please choose an other one.");
        return toReturn;
    }

    public ErrorResponseModel usernameIsEmpty(TribesUser user){
            ErrorResponseModel toReturn = new ErrorResponseModel();
            toReturn.setStatus("error");
            toReturn.setErrorMessage("Missing parameter(s): "+getErrorParameter(user));
            return toReturn;
    }

    public String getErrorParameter (TribesUser user){
        String toReturn = "";
        if (user.getUsername().isEmpty() || user.getUsername() == null){
            toReturn += " username ";
            return toReturn;
        }
        else if (user.getPassword().isEmpty() || user.getPassword() == null){
            toReturn += " password ";
            return toReturn;
        }
        return null;
    }
}
