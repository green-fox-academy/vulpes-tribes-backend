
package com.tribesbackend.tribes.tribesuser.controller;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribesuser.errorservice.ErrorMessagesMethods;
import com.tribesbackend.tribes.tribesuser.exception.InvalidUserPasswordException;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import com.tribesbackend.tribes.tribesuser.model.UserModelHelpersMethods;
import com.tribesbackend.tribes.tribesuser.okstatusservice.OKstatus;
import com.tribesbackend.tribes.tribesuser.okstatusservice.RandomToken;
import com.tribesbackend.tribes.tribesuser.repository.UserTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {
    UserTRepository userTRepository;
    UserModelHelpersMethods userMethods;
    ErrorMessagesMethods errorMessages;
    RandomToken randomToken;

    @Autowired
    public UserRestController(UserTRepository userTRepository, UserModelHelpersMethods userMethods, ErrorMessagesMethods errorMessages) {
        this.userTRepository = userTRepository;
        this.userMethods = userMethods;
        this.errorMessages = errorMessages;
    }
  
    @PostMapping(value = "/register")
    public ResponseEntity<Object> registerUser(@Validated @RequestBody TribesUser newUser) {

        if (userMethods.usernameAlreadyTaken(newUser)) {
            return new ResponseEntity(errorMessages.usernameAlreadyTaken(), HttpStatus.CONFLICT);
        } else if (newUser.getUsername() == null || newUser.getUsername().isEmpty()) {
            return new ResponseEntity(errorMessages.jsonFieldIsEmpty(newUser), HttpStatus.BAD_REQUEST);
        } else userTRepository.save(newUser);
        return ResponseEntity.ok(newUser);
        // return new ResponseEntity(newUser, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity loginUser(@RequestBody TribesUser tribesUser) {
        if (tribesUser.getUsername() == null || tribesUser.getUsername().isEmpty() ||
                tribesUser.getPassword() == null || tribesUser.getPassword().isEmpty()) {
            return new ResponseEntity(errorMessages.jsonFieldIsEmpty(tribesUser), HttpStatus.BAD_REQUEST);

        } else
            //if (userMethods.isValid(tribesUser)) {
            //username is in database andpassword matches
            if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()) == null) {
                //  throw new InvalidUserPasswordException("error", "Not such user: " + tribesUser.getUsername());
                return new ResponseEntity(
                        new InvalidUserPasswordException("error", "Not such user: " + tribesUser.getUsername())
                        , HttpStatus.UNAUTHORIZED);
            } else if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()).getPassword() ==
                    tribesUser.getPassword()) {
                return new ResponseEntity(
                        new OKstatus("ok", randomToken.getRandomToken())
                        , HttpStatus.OK);
                //username is not empty, but is not in database
            } else if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()).getPassword() !=
                    tribesUser.getPassword()) {
                //     throw new InvalidUserPasswordException("error", "Wrong password!");
                return new ResponseEntity(
                        new InvalidUserPasswordException("error", "Wrong password!")
                        , HttpStatus.UNAUTHORIZED);
                //just to have a return statement, will never happen
            }return new ResponseEntity(HttpStatus.CONFLICT);
    }
}
