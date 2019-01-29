package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.models.resourcesmodels.ResourcesModelListResponse;
import com.tribesbackend.tribes.repositories.UserTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourcesRestController {
    UserTRepository userTRepository;

    @Autowired
    public ResourcesRestController (UserTRepository userTRepository){
        this.userTRepository = userTRepository;

    }

    @GetMapping(value = "/kingdom/resources")
    public  ResponseEntity getResources (ResourcesModelListResponse resourcesModelListResponse) {
        TribesUser user = userTRepository.findTribesUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        ResourcesModelListResponse response = new ResourcesModelListResponse(user.getKingdom().getResourcesModel());
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
