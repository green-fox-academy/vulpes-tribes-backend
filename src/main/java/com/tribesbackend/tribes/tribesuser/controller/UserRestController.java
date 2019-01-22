//package com.tribesbackend.tribes.tribesuser.controller;
//
//import com.tribesbackend.tribes.repositories.KingdomRepository;
//import com.tribesbackend.tribes.services.okstatusservice.OKstatus;
//import com.tribesbackend.tribes.services.responseservice.ErrorMessagesMethods;
//import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
//import com.tribesbackend.tribes.services.userservice.LogoutMessages;
//import com.tribesbackend.tribes.services.userservice.UserModelHelpersMethods;
//import com.tribesbackend.tribes.tribesuser.model.TribesUser;
//import com.tribesbackend.tribes.tribesuser.repository.UserTRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
////@SuppressWarnings("unchecked")
//@RestController
//public class UserRestController {
//    UserTRepository userTRepository;
//    ErrorMessagesMethods errorMessages;
//    UserModelHelpersMethods
//    KingdomRepository kingdomRepo;
//
//    @Autowired
//    public UserRestController(UserTRepository userTRepository, ErrorMessagesMethods errorMessages) {
//        this.userTRepository = userTRepository;
//        this.errorMessages = errorMessages;
//    }
//
//    @PostMapping(value = "/register")
//    public ResponseEntity<Object> registerUser(@Validated @RequestBody TribesUser newUser) {
//
//        if (userTRepository.findTribesUserByUsername(newUser.getUsername()).equals(newUser)) {
//            return new ResponseEntity(errorMessages.usernameAlreadyTaken(), HttpStatus.CONFLICT);
//        } else userTRepository.save(newUser);
//        return ResponseEntity.ok(newUser);
//        // return new ResponseEntity(newUser, HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/login")
//    public ResponseEntity loginUser(@RequestBody TribesUser tribesUser) {
//        if (tribesUser.getUsername() == null || tribesUser.getUsername().isEmpty()) {
//            return new ResponseEntity(new ErrorResponseModel("Username is missing!"), HttpStatus.BAD_REQUEST);
//        } else if (tribesUser.getPassword() == null || tribesUser.getPassword().isEmpty()) {
//            return new ResponseEntity(new ErrorResponseModel("Password is missing!"), HttpStatus.BAD_REQUEST);
//        } else if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()) == null) {
//            return new ResponseEntity(new ErrorResponseModel("Not such user: " + tribesUser.getUsername())
//                    , HttpStatus.UNAUTHORIZED);
//        } else if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()).getPassword()
//                .equals(tribesUser.getPassword())) {
//            loggedin = true;
//            return new ResponseEntity(
//                    new OKstatus("ok", "token")
//                    , HttpStatus.OK);
//            //username is not empty, but is not in database
//        } else if (!userTRepository.findTribesUserByUsername(tribesUser.getUsername()).getPassword().equals(tribesUser.getPassword())) {
//            //     throw new InvalidUserPasswordException("error", "Wrong password!");
//            return new ResponseEntity(
//                    new ErrorResponseModel("Wrong password!")
//                    , HttpStatus.UNAUTHORIZED);
//            //just to have a return statement, will never happen
//        }
//        return new ResponseEntity(HttpStatus.CONFLICT);
//    }
//
//    @DeleteMapping(value = "/logout")
//    public ResponseEntity logoutUser(@RequestHeader(name = "token", required = false) String token) {
//        if (token == null || token.isEmpty()) {
//            return new ResponseEntity(new LogoutMessages("Unauthorized request!"), HttpStatus.FORBIDDEN);
//        } else
//            loggedin = false;
//        return ResponseEntity.ok(new LogoutMessages("Logged out successfully!"));
//    }
//}
