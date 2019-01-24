package com.tribesbackend.tribes.controllers;


import com.auth0.jwt.JWT;
import com.tribesbackend.tribes.configurations.security.SecurityConstants;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.models.jsonmodels.RegistrationInputJson;
import com.tribesbackend.tribes.models.jsonmodels.RegistrationResponseJson;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.repositories.UserTRepository;
import com.tribesbackend.tribes.services.responseservice.ErrorMessagesMethods;
import com.tribesbackend.tribes.services.responseservice.OKstatus;
import com.tribesbackend.tribes.services.userservice.LogoutMessages;
import com.tribesbackend.tribes.services.userservice.UserCrudService;
import com.tribesbackend.tribes.services.userservice.UserModelHelpersMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.tribesbackend.tribes.configurations.security.SecurityConstants.EXPIRATION_TIME;


@SuppressWarnings("unchecked")

@RestController
public class UserRestController {
    UserTRepository userTRepository;
    UserModelHelpersMethods userMethods;
    UserCrudService userCrudService;
    KingdomRepository kingdomRepo;


    @Autowired
    public UserRestController(UserTRepository userTRepository, UserModelHelpersMethods userMethods,
                              UserCrudService userCrudService, KingdomRepository kingdomRepo) {
        this.userTRepository = userTRepository;
        this.userMethods = userMethods;
        this.userCrudService = userCrudService;
        this.kingdomRepo = kingdomRepo;
    }

    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@Validated @RequestBody RegistrationInputJson regjson) {
        TribesUser newUser = new TribesUser(regjson.getUsername(), regjson.getPassword());

        if (userMethods.usernameAlreadyTaken(newUser)) {
            return new ResponseEntity(ErrorMessagesMethods.usernameAlreadyTaken(), HttpStatus.CONFLICT);
        } else
            userTRepository.save(newUser);
        Kingdom newKingdom = new Kingdom(regjson.getKingdom(), newUser);
        newUser.setKingdom(newKingdom);
        System.out.println(newKingdom.getName());
        System.out.println(newKingdom.getId());
        kingdomRepo.save(newKingdom);
        System.out.println(newUser.getId());
        System.out.println(newUser.getUsername());
        System.out.println(newKingdom.getId());
        return new ResponseEntity(new RegistrationResponseJson(newUser.getId(), newUser.getUsername(),
                newKingdom.getId(), "No avatar yet", 0), HttpStatus.OK);

    }

    @PostMapping(value = "/login")
    public ResponseEntity loginUser(@RequestBody TribesUser tribesUser) {
        if (tribesUser.getUsername() == null || tribesUser.getUsername().isEmpty() ||
                tribesUser.getPassword() == null || tribesUser.getPassword().isEmpty()) {
            return new ResponseEntity(ErrorMessagesMethods.jsonFieldIsEmpty(tribesUser), HttpStatus.BAD_REQUEST);


        } else if (!userTRepository.findTribesUserByUsername(tribesUser.getUsername()).isPresent()) {
            return new ResponseEntity(ErrorMessagesMethods.notSuchUser(tribesUser.getUsername()), HttpStatus.UNAUTHORIZED);
        } else if (userTRepository.findTribesUserByUsername(tribesUser.getUsername()).get().getPassword().equals(tribesUser.getPassword())) {
            userTRepository.findTribesUserByUsername(tribesUser.getUsername()).get().setLoggedIn(true);
            userTRepository.save(userTRepository.findTribesUserByUsername(tribesUser.getUsername()).get());
            return new ResponseEntity(
                    new OKstatus(
                            JWT.create()
                                    .withSubject(tribesUser.getUsername())
                                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                                    .sign(HMAC512(SecurityConstants.SECRET.getBytes())))
                    , HttpStatus.OK);
        } else if (!userTRepository.findTribesUserByUsername(tribesUser.getUsername()).get().getPassword()
                .equals(tribesUser.getPassword())) {
            return new ResponseEntity(ErrorMessagesMethods.wrongPassword()
                    , HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @DeleteMapping(value = "/logout")
    public ResponseEntity logoutUser() {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            userTRepository.findTribesUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                    //uncatched get
                    .get().setLoggedIn(false);
            return new ResponseEntity(new LogoutMessages("Logged out successfully!"), HttpStatus.OK);
        } else
            return new ResponseEntity(new LogoutMessages("Unauthorized request!"), HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "/user/testjwt")
    public String testingEndpoint() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
