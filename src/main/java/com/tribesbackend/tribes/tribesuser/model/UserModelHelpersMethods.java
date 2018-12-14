package com.tribesbackend.tribes.tribesuser.model;
import com.tribesbackend.tribes.tribesuser.repository.UserTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    public boolean usernameAlreadyTaken(TribesUser userFromJSON) {
        Optional<TribesUser> optionalUserFromRepo = userRepo.findByUsername(userFromJSON.getUsername());
        return (optionalUserFromRepo.isPresent());
    }
}


