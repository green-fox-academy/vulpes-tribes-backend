
package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import com.tribesbackend.tribes.security.JWTService;
import com.tribesbackend.tribes.services.KingdomService;
import com.tribesbackend.tribes.services.resourcesservice.ResourceCrudService;
import com.tribesbackend.tribes.services.resourcesservice.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourcesRestController {
    ResourceRepository resourceRepository;
    KingdomRepository kingdomRepository;
    ResourceCrudService resourceCrudService;
    KingdomService kingdomService;
    ResourceService resourceService;

    @Autowired
    public ResourcesRestController (ResourceRepository resourceRepository, KingdomRepository kingdomRepository,
                                    ResourceCrudService resourceCrudService, KingdomService kingdomService, ResourceService resourceService ){
        this.resourceRepository = resourceRepository;
        this.kingdomRepository = kingdomRepository;
        this.resourceCrudService = resourceCrudService;
        this.kingdomService = kingdomService;
        this.resourceService = resourceService;
    }

    @GetMapping(value = "/kingdom/resources")
    public ResponseEntity getBuilding(){
        String usname = SecurityContextHolder.getContext().getAuthentication().getName();
        Kingdom selectedOne = resourceService.resourceDisplayandUpdate(usname, 1);
        return ResponseEntity.ok(selectedOne.getResourcesModel());

    }
}
