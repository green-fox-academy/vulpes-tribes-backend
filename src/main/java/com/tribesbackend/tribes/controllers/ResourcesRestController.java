
package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.repositories.ResourceRepository;
import com.tribesbackend.tribes.services.KingdomService;
import com.tribesbackend.tribes.services.resourcesservice.ResourceCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourcesRestController extends BaseController {
    ResourceRepository resourceRepository;

    @Autowired
   public ResourcesRestController (ResourceRepository resourceRepository){
        this.resourceRepository = resourceRepository;
    }

    @GetMapping(value = "/kingdom/resources")
    public ResponseEntity getBuilding(){
        return new ResponseEntity(resourceRepository.findAllByKingdom(getCurrentKingdom()), HttpStatus.OK);
    }
}
