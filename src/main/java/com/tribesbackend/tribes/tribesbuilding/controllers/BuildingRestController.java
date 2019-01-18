package com.tribesbackend.tribes.tribesbuilding.controllers;

import com.tribesbackend.tribes.tribesbuilding.model.Building;
import com.tribesbackend.tribes.tribesbuilding.model.Buildings;
import com.tribesbackend.tribes.tribesbuilding.service.BuildingCrudService;
import com.tribesbackend.tribes.tribesbuilding.model.BuildingModelHelpersMethods;
import com.tribesbackend.tribes.tribesbuilding.repository.BuildingRepository;
import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribeskingdom.repository.KingdomRepository;
import com.tribesbackend.tribes.tribesuser.errorservice.ErrorMessagesMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class BuildingRestController {
    KingdomRepository kingdomRepository;
    BuildingRepository buildingRepository;
    BuildingModelHelpersMethods buildingModelHelpersMethods;
    ErrorMessagesMethods errorMessages;
    BuildingCrudService buildingCrudService;
    Buildings buildings;

    @Autowired
    public BuildingRestController (KingdomRepository kingdomRepository, BuildingRepository buildingRepository, BuildingModelHelpersMethods buildingModelHelpersMethods, ErrorMessagesMethods errorMessages, BuildingCrudService buildingCrudService ){
        this.kingdomRepository = kingdomRepository;
        this.buildingRepository = buildingRepository;
        this.buildingModelHelpersMethods = buildingModelHelpersMethods;
        this.errorMessages = errorMessages;
        this.buildingCrudService = buildingCrudService;
    }

    @GetMapping(value = "/kingdom/buildings")
    public ResponseEntity getBuilding(@RequestHeader(name = "username")String username){
        Kingdom selectedKingdom =  kingdomRepository.findKingdomByTribesUserUsername(username);
        buildings.setBuildingList(buildingRepository.findAllByKingdom(selectedKingdom).get());

//            List<Building> Buildings = new ArrayList<Building>();
//        Buildings.add(new Building("mine", 1, 12));
//        Buildings.add(new Building("farm", 1, 12));
//        Buildings.add(new Building("farm", 1, 12));
//        Buildings.add(new Building("mine", 1, 12));
            return new ResponseEntity(buildings.getBuildingList(), HttpStatus.OK);
    }
}
