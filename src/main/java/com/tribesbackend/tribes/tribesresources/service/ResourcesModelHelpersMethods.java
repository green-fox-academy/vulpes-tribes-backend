package com.tribesbackend.tribes.tribesresources.service;
import com.tribesbackend.tribes.tribesresources.model.ResourcesModel;
import com.tribesbackend.tribes.tribesresources.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourcesModelHelpersMethods {

    ResourceRepository resourceRepo;

    @Autowired
    public  ResourcesModelHelpersMethods (ResourceRepository resourceRepo) {this.resourceRepo = resourceRepo;}

    public static boolean isValid(ResourcesModel resourcesModel) {
        if (resourcesModel.getAmount()>=0&&resourcesModel.getType().equals("gold")){
            return true;
        }
        else return false;
    }
}
