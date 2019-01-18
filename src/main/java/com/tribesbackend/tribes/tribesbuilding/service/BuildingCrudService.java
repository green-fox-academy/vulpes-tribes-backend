package com.tribesbackend.tribes.tribesbuilding.service;

import com.tribesbackend.tribes.tribesbuilding.model.Building;
import com.tribesbackend.tribes.tribesbuilding.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingCrudService {
    BuildingRepository buildingRepository;

    @Autowired
    public BuildingCrudService (BuildingRepository buildingRepository) {this.buildingRepository = buildingRepository; }

    public  void save(Building newBuilding) throws Exception{
        buildingRepository.save(newBuilding);
    }
}
