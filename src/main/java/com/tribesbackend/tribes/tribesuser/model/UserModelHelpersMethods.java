package com.tribesbackend.tribes.tribesuser.model;

import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import com.tribesbackend.tribes.tribesuser.repository.UserTRepository;
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
        if (tribesUser.getUsername() == null || tribesUser.getPassword() == null) {
            return false;
        } else return true;
    }

<<<<<<< HEAD
//    public boolean usernameAlreadyTaken(TribesUser user) {
//        TribesUser toCompare = userRepo.findByUsername(user.getUsername());
//        if (user.getUsername().equals(toCompare.getUsername())) {
//            return true;
//        }
//        return false;
//    }
=======
    public boolean usernameAlreadyTaken(TribesUser userFromJSON) {
        Optional<TribesUser> optionalUserFromRepo = userRepo.findByUsername(userFromJSON.getUsername());
        return (optionalUserFromRepo.isPresent());
    }
>>>>>>> c7cb720e935e69b58199a883fe50c6b5a9ee6847
}


