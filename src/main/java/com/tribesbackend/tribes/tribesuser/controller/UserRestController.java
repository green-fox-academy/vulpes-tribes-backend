package com.tribesbackend.tribes.tribesuser.controller;

import com.tribesbackend.tribes.tribesuser.service.errorservice.ErrorMessagesMethods;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import com.tribesbackend.tribes.tribesuser.model.UserModelHelpersMethods;
import com.tribesbackend.tribes.tribesuser.service.OKstatus;
import com.tribesbackend.tribes.tribesuser.repository.UserTRepository;
import com.tribesbackend.tribes.tribesuser.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserRestController {
    UserTRepository userTRepository;
    UserModelHelpersMethods userMethods;
    UserCrudService userCrudService;

    @Autowired
    public UserRestController(UserTRepository userTRepository, UserModelHelpersMethods userMethods, UserCrudService userCrudService) {
        this.userTRepository = userTRepository;
        this.userMethods = userMethods;
        this.userCrudService = userCrudService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Object> registerUser(@Validated @RequestBody TribesUser newUser) {

        if (userMethods.usernameAlreadyTaken(newUser)) {
            return new ResponseEntity(ErrorMessagesMethods.usernameAlreadyTaken(), HttpStatus.CONFLICT);
        } else userTRepository.save(newUser);
        return ResponseEntity.ok(newUser);
        // return new ResponseEntity(newUser, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity loginUser(@RequestBody TribesUser tribesUser) {
        if (tribesUser.getUsername() == null || tribesUser.getUsername().isEmpty() ||
                tribesUser.getPassword() == null || tribesUser.getPassword().isEmpty()) {
            return new ResponseEntity(ErrorMessagesMethods.jsonFieldIsEmpty(tribesUser), HttpStatus.BAD_REQUEST);

        } else
            //if (userMethods.isValid(tribesUser)) {
            //username is in database and password matches
            if (!userTRepository.findTribesUserByUsername(tribesUser.getUsername()).isPresent()) {
                return new ResponseEntity(ErrorMessagesMethods.notSuchUser(tribesUser.getUsername()), HttpStatus.UNAUTHORIZED);
            } else if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()).get().getPassword().equals(tribesUser.getPassword())) {
                return new ResponseEntity(
                        new OKstatus("token")
                        , HttpStatus.OK);
                //username is not empty, but is not in database
            } else if (!userTRepository.findTribesUserByUsername(tribesUser.getUsername()).get().getPassword().equals(tribesUser.getPassword())) {
                return new ResponseEntity(ErrorMessagesMethods.wrongPassword()
                        , HttpStatus.UNAUTHORIZED);
                //just to have a return statement, will never happen
            }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }
}
