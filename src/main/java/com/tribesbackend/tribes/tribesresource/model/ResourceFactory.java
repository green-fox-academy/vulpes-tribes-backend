package com.tribesbackend.tribes.tribesresource.model;

import com.tribesbackend.tribes.tribesresource.repository.ResourceRepository;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ResourceFactory {
    ResourceRepository resourceRepository;

    @Autowired
    public ResourceFactory(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Timestamp getCurrentTimestamp(){
        return new Timestamp(System.currentTimeMillis());
    }
}
