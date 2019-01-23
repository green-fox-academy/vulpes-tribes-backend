package com.tribesbackend.tribes.controllers;


import com.tribesbackend.tribes.models.resourcesmodels.ResourcesModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mock")
public class MockResourcesController {

    @GetMapping(value = "/resources")
    public ResponseEntity getMockResources() {
        return ResponseEntity.ok(mockResources);
    }
    ResourcesModel mockResources = new ResourcesModel.ResourcesBuilder()
            .setAmount( 20 )
            .setType("gold")
            .build();
}
