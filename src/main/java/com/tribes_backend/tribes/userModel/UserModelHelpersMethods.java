package com.tribes_backend.tribes.userModel;

import com.tribes_backend.tribes.repository.UserTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserModelHelpersMethods {
    UserTRepository userRepo;
    @Autowired
    public UserModelHelpersMethods(UserTRepository userRepo) {
        this.userRepo = userRepo;
    }

    // to be specified later - jsousek
    public boolean checked (){
        return false;
    }

    public boolean usernameAlreadyTaken (TribesUser user){
        TribesUser toCompare = userRepo.findByUsername(user.getUsername());
        if (user.getUsername().equals(toCompare.getUsername())){
            return true;
        }
        return false;
    }
    public String getErrorParameter (TribesUser user){
        String toReturn = "";
        if (user.getUsername().isEmpty() || user.getUsername() == null){
            toReturn = "username";
            return toReturn;
        }
        else if (user.getPassword().isEmpty() || user.getPassword() == null){
            toReturn = "password";
            return toReturn;
        }
        return null;
    }
}
