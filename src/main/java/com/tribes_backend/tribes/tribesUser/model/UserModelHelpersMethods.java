package com.tribes_backend.tribes.tribesUser.model;


import com.tribes_backend.tribes.tribesUser.repository.UserTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserModelHelpersMethods {
    UserTRepository userRepo;

    @Autowired
    public UserModelHelpersMethods(UserTRepository userRepo) {
        this.userRepo = userRepo;
    }

    public static boolean isValid(TribesUser tribesUser) {
        if (tribesUser.getUsername() == null || tribesUser.getPassword() == null) {
            return false;
        } else return true;
    }

    public boolean usernameAlreadyTaken(TribesUser user) {
        TribesUser toCompare = userRepo.findByUsername(user.getUsername());
        if (user.getUsername().equals(toCompare.getUsername())) {
            return true;
        }
        return false;
    }

}

