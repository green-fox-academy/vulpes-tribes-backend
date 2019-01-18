package com.tribesbackend.tribes.tribesuser.controller;

import com.auth0.jwt.JWT;
import com.tribesbackend.tribes.security.SecurityConstants;
import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribeskingdom.repository.KingdomRepository;
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


import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.tribesbackend.tribes.security.SecurityConstants.EXPIRATION_TIME;



@RestController
public class UserRestController {
    UserTRepository userTRepository;
    UserModelHelpersMethods userMethods;
    ErrorMessagesMethods errorMessages;
    UserCrudService userCrudService;
    KingdomRepository kingdomRepo;
    boolean loggedIn; 

    @Autowired
    public UserRestController(UserTRepository userTRepository, UserModelHelpersMethods userMethods, ErrorMessagesMethods errorMessages, UserCrudService userCrudService) {
        this.userTRepository = userTRepository;
        this.userMethods = userMethods;
        this.errorMessages = errorMessages;
        this.userCrudService = userCrudService;
    }

    @GetMapping (value="/mockuser")
    public ResponseEntity sampleUserJson(){
        TribesUser tuser  = new TribesUser("jirina","1234");
        userTRepository.save(tuser);
        return new ResponseEntity(tuser, HttpStatus.OK);
    }



    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@Validated @RequestBody TribesUser newUser, Kingdom newKingdom) {

        if (userMethods.usernameAlreadyTaken(newUser)) {
            return new ResponseEntity(errorMessages.usernameAlreadyTaken(), HttpStatus.CONFLICT);
        } else

            userTRepository.save(newUser);
            kingdomRepo.save(newUser.ge)


        return new ResponseEntity(newUser, HttpStatus.OK);
        // return new ResponseEntity(newUser, HttpStatus.OK);

    }

    @PostMapping(value = "/login")
    public ResponseEntity loginUser(@RequestBody TribesUser tribesUser) {
        if (tribesUser.getUsername() == null || tribesUser.getUsername().isEmpty() ||
                tribesUser.getPassword() == null || tribesUser.getPassword().isEmpty()) {
            return new ResponseEntity(errorMessages.jsonFieldIsEmpty(tribesUser), HttpStatus.BAD_REQUEST);

        } else if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()) == null) {
                //  throw new InvalidUserPasswordException("error", "Not such user: " + tribesUser.getUsername());
                return new ResponseEntity(
                        new InvalidUserPasswordException("error", "Not such user: " + tribesUser.getUsername())
                        , HttpStatus.UNAUTHORIZED);
            } else if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()).getPassword().equals(tribesUser.getPassword())) {
                loggedIn = true;
                return new ResponseEntity(
                        new OKstatus("ok",
                                 JWT.create()
                                .withSubject(tribesUser.getUsername())
                                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                                .sign(HMAC512(SecurityConstants.SECRET.getBytes())))
                        , HttpStatus.OK);
            } else if (!userTRepository.findTribesUserByUsername(tribesUser.getUsername()).getPassword().equals(tribesUser.getPassword())) {
                return new ResponseEntity(
                        new InvalidUserPasswordException("error", "Wrong password!")
                        , HttpStatus.UNAUTHORIZED);
            }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @DeleteMapping(value = "/logout")
    public ResponseEntity logoutUser(@RequestHeader(name = "token", required = false) String token) {
        if (token == null || token.isEmpty()) {
            return new ResponseEntity(new LogoutMessages("Unauthorized request!"), HttpStatus.FORBIDDEN);
        } else
            loggedIn = false;
            return ResponseEntity.ok(new LogoutMessages("Logged out successfully!"));
    }

//    @GetMapping (value = "/testjwt")
//    public String testingEndpoint(@RequestHeader (name = "token", required = false)
//                                              UsernamePasswordAuthenticationToken authentication){
//
//
//     //   UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
//      return SecurityContextHolder.getContext().getAuthentication(authentication);
//
//         //     JWT.decode(token).getSubject();
//    }
}
