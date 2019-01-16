package com.tribesbackend.tribes.tribesbuilding.controller;

import com.tribesbackend.tribes.tribesbuilding.model.Building;
import com.tribesbackend.tribes.tribesbuilding.model.BuildingFactory;
import com.tribesbackend.tribes.tribesbuilding.repository.BuildingRepository;
import com.tribesbackend.tribes.tribesbuilding.service.ErrorMessage;
import com.tribesbackend.tribes.tribesuser.okstatusservice.TokenIsValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MockBuildingRestController {
    BuildingRepository buldingRepo;

    @Autowired
    MockBuildingRestController(BuildingRepository buildingRepo) {
        this.buldingRepo = buildingRepo;
    }

    @GetMapping(value = "/kingdom/buildings")
    public ResponseEntity<Object> buildingsOfUser( @RequestHeader (name = "X-Tribes-Token") String xTribesToken) {
        List<Building> usersBuildings = new ArrayList<Building>();
        usersBuildings.add(BuildingFactory.createSampleBuilding());
        if (TokenIsValid.isValid(xTribesToken)) {
            return new ResponseEntity(usersBuildings, HttpStatus.OK);
        } else return new ResponseEntity("Invalid token", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/kingdom/buildings")
    public ResponseEntity<Object> sendBuildings(@Validated String xTribesToken, @RequestBody String type) {
        if (type.equals("farm") || type.equals("mine") || type.equals("barrack") || type.equals("barrack")) {
            return new ResponseEntity(new Building(type), HttpStatus.OK);
        }
        else if (type == null || type.equals("")) {
            return new ResponseEntity(new ErrorMessage("error", "Missing parameter(s): type!"), HttpStatus.BAD_REQUEST);
        }
        else if ((type.equals("farm") || type.equals("mine") || type.equals("barrack") || type.equals("barrack"))) {
            return new ResponseEntity(new ErrorMessage("error", "Invalid building type"), HttpStatus.NOT_ACCEPTABLE);
        }
        //not enough resources
        else if (false) {
            return new ResponseEntity(new ErrorMessage("error", "Not enough resources"), HttpStatus.CONFLICT);
        }
        else return new ResponseEntity(new ErrorMessage("error", "Unexpected error"), HttpStatus.IM_USED);
    }

    @GetMapping(value = "/kingdom/buildings/{id}")
    public @ResponseBody
    ResponseEntity<Object> listTheBuilding(@Validated @PathVariable int id, String xTribesToken) {
        if (TokenIsValid.isValid(xTribesToken)) {
            return new ResponseEntity(buldingRepo.findById(id), HttpStatus.OK);
        } else return new ResponseEntity(new ErrorMessage("error", "Id not found"),
                HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/kingdom/buildings/{id}")
    public ResponseEntity<Object> upgradeOrDowngradeBuilding(@Validated @PathVariable int id,
                                                             @RequestBody String xTribesToken, Integer typeLevel) {
        // and enough resources
        if (TokenIsValid.isValid(xTribesToken) && (true)) {
            buldingRepo.findById(id).get().setHP(typeLevel);
            buldingRepo.save(buldingRepo.findById(id).get());
            return new ResponseEntity(buldingRepo.findById(id).get(), HttpStatus.OK);
        } else if (typeLevel < 0 || typeLevel == null || typeLevel.toString().isEmpty()) {
            return new ResponseEntity(new ErrorMessage("error", "Missing parameter(s): !"), HttpStatus.BAD_REQUEST);
        } else if (!(buldingRepo.findById(id).isPresent())) {
            return new ResponseEntity(new ErrorMessage("error", "Id not found"), HttpStatus.NOT_FOUND);
        }
        //not valid level of the building
        else if (false) {
            return new ResponseEntity(new ErrorMessage("error", "Invalid building level"), HttpStatus.NOT_ACCEPTABLE);
        }
        //not enough resource
        else if (false) {
            return new ResponseEntity(new ErrorMessage("error", "Not enough resource"), HttpStatus.CONFLICT);
        } else return new ResponseEntity(new ErrorMessage("error", "Unexpected error"), HttpStatus.IM_USED);
    }
}
