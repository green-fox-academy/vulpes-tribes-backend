package com.tribesbackend.tribes.models.resourcesmodels;

import com.tribesbackend.tribes.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
