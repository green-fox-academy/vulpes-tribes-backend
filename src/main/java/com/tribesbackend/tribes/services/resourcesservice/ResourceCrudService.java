package com.tribesbackend.tribes.services.resourcesservice;

import com.tribesbackend.tribes.models.ResourcesModel;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceCrudService {
    ResourceRepository resourceRepository;

    @Autowired
    public ResourceCrudService (ResourceRepository resourceRepository) {this.resourceRepository = resourceRepository; }

    public  void save(ResourcesModel newResource) throws Exception{
        resourceRepository.save(newResource);
    }
}
