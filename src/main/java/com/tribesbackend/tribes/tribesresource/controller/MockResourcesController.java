package com.tribesbackend.tribes.tribesresource.controller;

import com.tribesbackend.tribes.tribesresource.model.Resources;
import com.tribesbackend.tribes.tribesresource.model.ResourcesFactory;
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
    Resources mockResources = ResourcesFactory.createValidSampleResources();
}