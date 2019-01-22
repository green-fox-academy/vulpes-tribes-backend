package com.tribesbackend.tribes.services.userservice;

import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.repositories.UserTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserModelHelpersMethods {
    UserTRepository userRepo;

    @Autowired
    public UserModelHelpersMethods(UserTRepository userRepo) {
        this.userRepo = userRepo;
    }

    public static boolean isValid(TribesUser tribesUser) {
        if (tribesUser.getUsername() == null || tribesUser.getPassword() == null ||
                tribesUser.getUsername().equals("") || tribesUser.getPassword().equals("")) {
            return false;
        } else return true;
    }

    public boolean usernameAlreadyTaken(TribesUser userFromJSON) {
        Optional<TribesUser> optionalUserFromRepo = userRepo.findTribesUserByUsername(userFromJSON.getUsername());
        return (optionalUserFromRepo.isPresent());
    }
}
