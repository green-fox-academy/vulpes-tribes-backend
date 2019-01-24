
package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import com.tribesbackend.tribes.services.resourcesservice.ResourceCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourcesRestController {
    ResourceRepository resourceRepository;
    KingdomRepository kingdomRepository;
    ResourceCrudService resourceCrudService;

    @Autowired
    public ResourcesRestController (ResourceRepository resourceRepository, KingdomRepository kingdomRepository,
                                    ResourceCrudService resourceCrudService ){
        this.resourceRepository = resourceRepository;
        this.kingdomRepository = kingdomRepository;
        this.resourceCrudService = resourceCrudService;
    }
//
//    @GetMapping(value = "/kingdom/resources")
//    public ResponseEntity getBuilding(@RequestHeader(name = "username")String username){
//        Kingdom selectedKingdom =  kingdomRepository.findKingdomByTribesUserUsername(username);
//        return new ResponseEntity(resourceRepository.findAllByKingdom(selectedKingdom), HttpStatus.OK);
//    }
}
