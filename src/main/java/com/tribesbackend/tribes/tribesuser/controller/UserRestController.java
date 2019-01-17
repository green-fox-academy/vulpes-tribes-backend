package com.tribesbackend.tribes.tribesuser.controller;

import com.tribesbackend.tribes.logging.Logging;
import com.tribesbackend.tribes.tribesuser.errorservice.ErrorMessagesMethods;
import com.tribesbackend.tribes.tribesuser.exception.InvalidUserPasswordException;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import com.tribesbackend.tribes.tribesuser.model.UserModelHelpersMethods;
import com.tribesbackend.tribes.tribesuser.okstatusservice.OKstatus;
import com.tribesbackend.tribes.tribesuser.repository.UserTRepository;
import com.tribesbackend.tribes.tribesuser.service.LogoutMessages;
import com.tribesbackend.tribes.tribesuser.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unchecked")
@RestController
public class UserRestController extends Logging {
    UserTRepository userTRepository;
    UserModelHelpersMethods userMethods;
    ErrorMessagesMethods errorMessages;
    UserCrudService userCrudService;
    boolean loggedIn; 

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
            LOGGER.error("POST /register username is already taken for username: "+ newUser.getUsername());
            return new ResponseEntity(errorMessages.usernameAlreadyTaken(), HttpStatus.CONFLICT);
        } else userTRepository.save(newUser);
        LOGGER.info("POST /register new user with username {} registered", newUser.getUsername());
        return ResponseEntity.ok(newUser);
    }

    @PostMapping(value = "/login")
    public ResponseEntity loginUser(@RequestBody TribesUser tribesUser) {
        if (tribesUser.getUsername() == null || tribesUser.getUsername().isEmpty() ||
                tribesUser.getPassword() == null || tribesUser.getPassword().isEmpty()) {
            LOGGER.error("POST /login empty json field");
            return new ResponseEntity(errorMessages.jsonFieldIsEmpty(tribesUser), HttpStatus.BAD_REQUEST);

        } else if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()) == null) {
                //  throw new InvalidUserPasswordException("error", "Not such user: " + tribesUser.getUsername());
                LOGGER.error("POST /login not such user: "+tribesUser.getUsername());
                return new ResponseEntity(
                        new InvalidUserPasswordException("error", "Not such user: " + tribesUser.getUsername())
                        , HttpStatus.UNAUTHORIZED);
            } else if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()).getPassword().equals(tribesUser.getPassword())) {
                loggedIn = true;
                LOGGER.info("POST /login username registered with username: "+tribesUser.getUsername());
                return new ResponseEntity(
                        new OKstatus("ok", "token")
                        , HttpStatus.OK);
            } else if (!userTRepository.findTribesUserByUsername(tribesUser.getUsername()).getPassword().equals(tribesUser.getPassword())) {
            LOGGER.error("POST /login username: "+tribesUser.getUsername()+" is with wrong password");
            return new ResponseEntity(
                        new InvalidUserPasswordException("error", "Wrong password!")
                        , HttpStatus.UNAUTHORIZED);
            }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @DeleteMapping(value = "/logout")
    public ResponseEntity logoutUser(@RequestHeader(name = "token", required = false) String token) {
        if (token == null || token.isEmpty()) {
            LOGGER.error("DELETE /login Empty token, unauthorized request");
            return new ResponseEntity(new LogoutMessages("Unauthorized request!"), HttpStatus.FORBIDDEN);
        } else
            loggedIn = false;
            LOGGER.info("DELETE /logout  Logged out successfully");
            return ResponseEntity.ok(new LogoutMessages("Logged out successfully!"));
    }
}
