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

    public Object usernameIsEmpty(TribesUser user){
        if (user.getUsername()==null || user.getUsername().isEmpty()){
            ErrorResponseModel toReturn = new ErrorResponseModel();
            toReturn.setStatus("error");
            toReturn.setErrorMessage("Missing parameter(s): username!");
            return toReturn;
        }
        else return user;
    }
}
