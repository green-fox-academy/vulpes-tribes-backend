
package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Resources.Resources;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import com.tribesbackend.tribes.security.JWTService;
import com.tribesbackend.tribes.services.KingdomService;
import com.tribesbackend.tribes.services.resourcesservice.ResourceCrudService;
import com.tribesbackend.tribes.services.resourcesservice.ResourceService;
import com.tribesbackend.tribes.services.responseservice.ErrorMessagesMethods;
import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
import com.tribesbackend.tribes.services.responseservice.ResponseService;
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
    ErrorMessagesMethods errorMessagesMethods;

    @Autowired
    public ResourcesRestController (ResourceRepository resourceRepository, KingdomRepository kingdomRepository,
                                    ResourceCrudService resourceCrudService, KingdomService kingdomService,
                                    ResourceService resourceService, ErrorMessagesMethods errorMessagesMethods ){
        this.resourceRepository = resourceRepository;
        this.kingdomRepository = kingdomRepository;
        this.resourceCrudService = resourceCrudService;
        this.kingdomService = kingdomService;
        this.resourceService = resourceService;
        this.errorMessagesMethods = errorMessagesMethods;
    }

    @GetMapping(value = "/kingdom/resources")
    public ResponseEntity getBuilding(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userName == null || userName.isEmpty()){
            return new ResponseEntity(errorMessagesMethods.jsonUsernameNotProvided(), HttpStatus.BAD_REQUEST);
        }
        Kingdom selectedOne = resourceService.resourceDisplayandUpdate(userName, 1);
        Resources resources = new Resources();
        resources.setResources(selectedOne.getResourcesModel());
        return ResponseEntity.ok(resources);
    }
}
