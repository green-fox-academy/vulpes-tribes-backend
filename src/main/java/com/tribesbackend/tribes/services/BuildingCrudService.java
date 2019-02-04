package com.tribesbackend.tribes.services;

import com.tribesbackend.tribes.controllers.BaseController;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.buildingmodels.Building;
import com.tribesbackend.tribes.repositories.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingCrudService extends BaseController {
    BuildingRepository buildingRepository;

    @Autowired
    public BuildingCrudService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public void save(Building newBuilding) throws Exception {
        buildingRepository.save(newBuilding);
    }

    public Building createAndSaveBuilding(String type, Kingdom kingdom) {
            Building newBuilding = new Building(type, kingdom);
            buildingRepository.save(newBuilding);
            return  newBuilding;
    }
}
