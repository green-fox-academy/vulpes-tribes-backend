

package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Resources.Resources;
import com.tribesbackend.tribes.models.Resources.ResourcesModel;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import com.tribesbackend.tribes.services.KingdomService;
import com.tribesbackend.tribes.services.resourcesservice.ResourceCrudService;
import com.tribesbackend.tribes.services.resourcesservice.ResourceService;
import com.tribesbackend.tribes.services.responseservice.ErrorMessagesMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResourcesRestController {
    ResourceService resourceService;
    ErrorMessagesMethods errorMessagesMethods;
    KingdomRepository kingdomRepository;

    @Autowired
    public ResourcesRestController(ResourceService resourceService, ErrorMessagesMethods errorMessagesMethods,
                                   KingdomRepository kingdomRepository) {
        this.resourceService = resourceService;
        this.errorMessagesMethods = errorMessagesMethods;
        this.kingdomRepository = kingdomRepository;
    }

    @GetMapping(value = "/kingdom/resources")
    public ResponseEntity getResources() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userName == null || userName.isEmpty()) {
            return new ResponseEntity(errorMessagesMethods.jsonUsernameNotProvided(), HttpStatus.BAD_REQUEST);
        }
        List<ResourcesModel> updatedList = resourceService.resourceDisplayandUpdate(userName, 60);
        Resources resources = new Resources();
        resources.setResources(updatedList);
        return ResponseEntity.ok(resources);
    }
}
