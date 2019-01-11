package com.tribesbackend.tribes.tribesresource.model;

import com.tribesbackend.tribes.tribesresource.repository.ResourceRepository;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

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

    public Timestamp getLastTimestampFromDB (Resource verifiedResource) {
        return new Timestamp(verifiedResource.getTimeStampLastVisit());
    }

    public Resource verifyResource (long id){
        Optional<Resource> optionalResource = resourceRepository.findByResources_id(id);
        if (optionalResource.isPresent()){
            return optionalResource.get();
        }
        else throw new IllegalArgumentException();

        //return Optional.ofNullable(optionalResource)
          //      .map(r -> r.get())
            //    .orElseThrow(IllegalArgumentException::new);
    }

}
