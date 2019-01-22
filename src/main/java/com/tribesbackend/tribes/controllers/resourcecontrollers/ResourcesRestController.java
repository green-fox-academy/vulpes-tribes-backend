package com.tribesbackend.tribes.controllers.resourcecontrollers;

import com.tribesbackend.tribes.models.resourcesmodels.ResourcesModelBuilder;
import com.tribesbackend.tribes.models.resourcesmodels.ResourcesModelConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResourcesRestController {

    @PostMapping(value = "/kingdom/resources")
    public ResponseEntity getresources (ResourcesModelBuilder resourcesModelBuilder) {
        List<ResourcesModelConstructor> resources = new ArrayList<ResourcesModelConstructor>();
        resources.add(new ResourcesModelConstructor("gold",10,1));
        resources.add(new ResourcesModelConstructor("food",10,1));
        ResourcesModelBuilder example = new ResourcesModelBuilder(resources);
        return new ResponseEntity(example, HttpStatus.OK);
    }
}
