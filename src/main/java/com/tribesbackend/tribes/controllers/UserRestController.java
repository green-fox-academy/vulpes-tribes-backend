package com.tribesbackend.tribes.controllers;


import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.ResourcesModel;
import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.models.jsonmodels.RegistrationInputJson;
import com.tribesbackend.tribes.models.jsonmodels.RegistrationResponseJson;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import com.tribesbackend.tribes.repositories.UserTRepository;
import com.tribesbackend.tribes.security.JWTService;
import com.tribesbackend.tribes.services.resourcesservice.ResourceService;
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

import java.util.List;


@SuppressWarnings("unchecked")

@RestController
public class UserRestController {
    UserTRepository userTRepository;
    UserModelHelpersMethods userMethods;
    UserCrudService userCrudService;
    KingdomRepository kingdomRepo;
    ResourceService resourceService;
    ResourceRepository resourceRepository;

    @Autowired
    public UserRestController(UserTRepository userTRepository, UserModelHelpersMethods userMethods,
                              UserCrudService userCrudService, KingdomRepository kingdomRepo,
                              ResourceService resourceService, ResourceRepository resourceRepository) {
        this.userTRepository = userTRepository;
        this.userMethods = userMethods;
        this.userCrudService = userCrudService;
        this.kingdomRepo = kingdomRepo;
        this.resourceService = resourceService;
        this.resourceRepository = resourceRepository;
    }

    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@Validated @RequestBody RegistrationInputJson regjson) {
        TribesUser newUser = new TribesUser(regjson.getUsername(), regjson.getPassword());

        if (userMethods.usernameAlreadyTaken(newUser)) {
            return new ResponseEntity(ErrorMessagesMethods.usernameAlreadyTaken(), HttpStatus.CONFLICT);
        }
        userTRepository.save(newUser);

        Kingdom newKingdom = new Kingdom(regjson.getKingdom(), newUser);
//        newUser.setKingdom(newKingdom);
        kingdomRepo.save(newKingdom);
        List<ResourcesModel> newResources = resourceService.newUserResourcesPreFill(newKingdom);
        newResources.forEach(resourcesModel -> resourceRepository.save(resourcesModel));
        //newKingdom.setResourcesModel(newResources);


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

            TribesUser user = userTRepository.findTribesUserByUsername(tribesUser.getUsername()).get();
            user.setLoggedIn(true);
            userTRepository.save(user);

            return new ResponseEntity(
                    new OKstatus(JWTService.createToken(tribesUser.getUsername())),HttpStatus.OK);
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
            TribesUser user = userTRepository.findTribesUserByUsername(SecurityContextHolder.getContext()
                    .getAuthentication().getName()).get();
            user.setLoggedIn(false);
            userTRepository.save(user);
            return new ResponseEntity(new LogoutMessages("ok","Logged out successfully!"), HttpStatus.OK);
        } else
            return new ResponseEntity(new LogoutMessages("error","Unauthorized request!"), HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "/user/testjwt")
    public String testingEndpoint() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
