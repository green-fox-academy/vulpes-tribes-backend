package com.tribesbackend.tribes.tribesuser.controller;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribesuser.errorservice.ErrorMessagesMethods;
import com.tribesbackend.tribes.tribesuser.exception.InvalidUserPasswordException;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import com.tribesbackend.tribes.tribesuser.model.UserModelHelpersMethods;
import com.tribesbackend.tribes.tribesuser.okstatusservice.OKstatus;
import com.tribesbackend.tribes.tribesuser.okstatusservice.RandomToken;
import com.tribesbackend.tribes.tribesuser.repository.UserTRepository;
import com.tribesbackend.tribes.tribesuser.service.LogoutMessages;
import com.tribesbackend.tribes.tribesuser.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserRestController {
    UserTRepository userTRepository;
    UserModelHelpersMethods userMethods;
    ErrorMessagesMethods errorMessages;
    UserCrudService userCrudService;

    @Autowired
    public UserRestController(UserTRepository userTRepository, UserModelHelpersMethods userMethods, ErrorMessagesMethods errorMessages, UserCrudService userCrudService) {
        this.userTRepository = userTRepository;
        this.userMethods = userMethods;
        this.errorMessages = errorMessages;
        this.userCrudService = userCrudService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Object> registerUser(@Validated @RequestBody TribesUser newUser) {

        if (userMethods.usernameAlreadyTaken(newUser)) {
            return new ResponseEntity(errorMessages.usernameAlreadyTaken(), HttpStatus.CONFLICT);
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
            } else if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()).getPassword().equals(tribesUser.getPassword())) {
                return new ResponseEntity(
                        new OKstatus("ok", "token")
                        , HttpStatus.OK);
                //username is not empty, but is not in database
            } else if (!userTRepository.findTribesUserByUsername(tribesUser.getUsername()).getPassword().equals(tribesUser.getPassword())) {
                //     throw new InvalidUserPasswordException("error", "Wrong password!");
                return new ResponseEntity(
                        new InvalidUserPasswordException("error", "Wrong password!")
                        , HttpStatus.UNAUTHORIZED);
                //just to have a return statement, will never happen
            }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @DeleteMapping(value = "/logout")
    public ResponseEntity logoutUser(@RequestHeader(name = "token", required = false) String token) {
        if(token == null || token.isEmpty()) {
            return new ResponseEntity(new LogoutMessages("Unauthorized request!"), HttpStatus.FORBIDDEN);
        }
        else return ResponseEntity.ok(new LogoutMessages("Logged out successfully!"));
    }
}
