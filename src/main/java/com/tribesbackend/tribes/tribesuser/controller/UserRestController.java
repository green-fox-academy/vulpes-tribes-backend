package com.tribesbackend.tribes.tribesuser.controller;


import com.tribesbackend.tribes.tribesuser.model.UserModelHelpersMethods;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;


import com.tribesbackend.tribes.tribesuser.errorservice.ErrorMessagesMethods;

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
    UserCrudService userCrudService;
    UserModelHelpersMethods userMethods;
    ErrorMessagesMethods errorMessages;

    @Autowired
    public UserRestController(UserCrudService userCrudService, UserModelHelpersMethods userMethods, ErrorMessagesMethods errorMessages) {
        this.userCrudService = userCrudService;
        this.userMethods = userMethods;
        this.errorMessages = errorMessages;
    }

//    @PostMapping(value = "/register")
//    public ResponseEntity<Object> registerUser ( @Validated  @RequestBody TribesUser newUser)  {
//
//        if (userMethods.usernameAlreadyTaken(newUser)){
//            return new ResponseEntity(errorMessages.usernameAlreadyTaken(), HttpStatus.CONFLICT);
//        }
//        else if (newUser.getUsername() == null || newUser.getUsername().isEmpty()){
//            return new ResponseEntity(errorMessages.jsonFieldIsEmpty(newUser), HttpStatus.BAD_REQUEST);
//        }
//        else userCrudService.save(newUser);
//        return ResponseEntity.ok(newUser);
//    }


//    @PostMapping(value = "/login")
//
//    public ResponseEntity loginUser(@RequestBody TribesUser tribesuser) {
//        if (userMethods.isValid(tribesuser)) {
//            if (userTRepository.findTribesUserByUsername(tribesuser.getUsername()).getPassword() ==
//                    tribesuser.getPassword()){
//                return new ResponseEntity(,HttpStatus.OK)
//            }
//
//
//        }
//
//    }
}
