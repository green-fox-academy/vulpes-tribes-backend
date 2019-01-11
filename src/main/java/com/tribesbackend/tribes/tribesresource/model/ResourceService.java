package com.tribesbackend.tribes.tribesresource.model;

import com.tribesbackend.tribes.service.TimeService;
import com.tribesbackend.tribes.tribesresource.repository.ResourceRepository;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class ResourceService {
    ResourceRepository resourceRepository;
    
    @Autowired
    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    private Timestamp getCurrentTimestamp(){
        return new Timestamp(System.currentTimeMillis());
    }

    private Timestamp getLastTimestampFromDB (Resource verifiedResource) {
        return new Timestamp(verifiedResource.getTimeStampLastVisit());
    }

    private Resource verifyResource (long id) {
        Optional<Resource> optionalResource = resourceRepository.findByResources_id(id);
        if (optionalResource.isPresent()) {
            return optionalResource.get();
        } else throw new IllegalArgumentException();
    }
        //return Optional.ofNullable(optionalResource)
          //      .map(r -> r.get())
            //    .orElseThrow(IllegalArgumentException::new);

    public long getDifferenceInMinutes (long id){
        return TimeService.timeDifferenceInMin(getLastTimestampFromDB(verifyResource(id)), getCurrentTimestamp());
    }

    public Resource resourceDisplayandUpdate (long id, int amountGeneratedPerMinute){
        Resource resource = verifyResource(id);
        long updatedResourceAmount = resource.getAmount() + getDifferenceInMinutes(id) * amountGeneratedPerMinute;
        resource.setAmount(updatedResourceAmount);
        resource.setTimeStampLastVisit(getCurrentTimestamp().getTime());
        resourceRepository.save(resource);
        return resource;
    }
}
