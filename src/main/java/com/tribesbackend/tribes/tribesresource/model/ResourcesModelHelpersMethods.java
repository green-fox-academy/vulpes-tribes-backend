package com.tribesbackend.tribes.tribesresource.model;

import com.tribesbackend.tribes.tribesresource.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ResourcesModelHelpersMethods {

    ResourceRepository resourceRepo;

    @Autowired
    public  ResourcesModelHelpersMethods (ResourceRepository resourceRepo) {this.resourceRepo = resourceRepo;}

    public static boolean isValid(Resources resources) {
        if (resources.getAmount()>=0&&resources.getUpdated_at()>=0&&resources.getType().equals("gold")){
            return true;}
        else return false;
    }
}
