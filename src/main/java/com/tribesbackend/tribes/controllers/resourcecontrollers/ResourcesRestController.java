package com.tribesbackend.tribes.controllers.resourcecontrollers;

import com.tribesbackend.tribes.models.resourcesmodels.ResourcesModelListResponse;
import com.tribesbackend.tribes.models.resourcesmodels.ResourcesModelResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ResourcesRestController {

    @GetMapping(value = "/mock/kingdom/resources")
    public ResponseEntity getResources (ResourcesModelListResponse resourcesModelListResponse) {
        List<ResourcesModelResponse> resources = new ArrayList<ResourcesModelResponse>();
        resources.add(new ResourcesModelResponse("gold",100,1));
        resources.add(new ResourcesModelResponse("food",100,1));
        ResourcesModelListResponse example = new ResourcesModelListResponse(resources);
        return new ResponseEntity(example, HttpStatus.OK);
    }
}
