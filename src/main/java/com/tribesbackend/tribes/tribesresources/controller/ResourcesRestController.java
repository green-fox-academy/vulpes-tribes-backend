
package com.tribesbackend.tribes.tribesresources.controller;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribeskingdom.repository.KingdomRepository;
import com.tribesbackend.tribes.tribesresources.repository.ResourceRepository;
import com.tribesbackend.tribes.tribesresources.service.ResourceCrudService;
import com.tribesbackend.tribes.tribesresources.service.ResourcesModelHelpersMethods;
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
    ResourcesModelHelpersMethods resourcesModelHelpersMethods;
    ResourceCrudService resourceCrudService;

    @Autowired
    public ResourcesRestController (ResourceRepository resourceRepository, KingdomRepository kingdomRepository, ResourcesModelHelpersMethods resourcesModelHelpersMethods,  ResourceCrudService resourceCrudService ){
        this.resourceRepository = resourceRepository;
        this.kingdomRepository = kingdomRepository;
        this.resourcesModelHelpersMethods = resourcesModelHelpersMethods;
        this.resourceCrudService = resourceCrudService;
    }

    @GetMapping(value = "/kingdom/resources")
    public ResponseEntity getBuilding(@RequestHeader(name = "username")String username){
        Kingdom selectedKingdom =  kingdomRepository.findKingdomByTribesUserUsername(username);
        return new ResponseEntity(resourceRepository.findAllByKingdom(selectedKingdom), HttpStatus.OK);
    }
}
