package com.tribesbackend.tribes.tribesbuilding.model;

import com.tribesbackend.tribes.tribesbuilding.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingModelHelpersMethods {
    BuildingRepository buildingRepo;

    @Autowired
    public  BuildingModelHelpersMethods (BuildingRepository buildingRepo) {
        this.buildingRepo = buildingRepo;
    }

    public static boolean isValid(Building building) {
        if (building.getType()==null||building.getType().equals("")){
            return false;}
        else return true;
    }

    public static boolean isEmpty(Building building) {
        return building.getType().isEmpty() || building.getType().equals(" ");
    }
}
