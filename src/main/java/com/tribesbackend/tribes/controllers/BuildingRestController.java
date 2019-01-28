
package com.tribesbackend.tribes.controllers;
import com.tribesbackend.tribes.models.buildingmodels.Building;
import com.tribesbackend.tribes.models.jsonmodels.BuildingInputJson;
import com.tribesbackend.tribes.repositories.BuildingRepository;
import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class BuildingRestController {
    BuildingRepository buildingRepo;

    @Autowired
    BuildingRestController(BuildingRepository buildingRepo) {
        this.buildingRepo = buildingRepo;
    }

    @GetMapping(value = "/kingdom/buildings")
    public ResponseEntity<Object> buildingsOfUser() {
        List<Building> usersBuildings = new ArrayList<Building>();
        usersBuildings.add(new Building("farm",1,10 ));
            return new ResponseEntity(usersBuildings, HttpStatus.OK);
    }

    @PostMapping(value = "/kingdom/buildings")
    public ResponseEntity<Object> sendBuildings(@RequestBody  String type) {
        if (type.equals("farm") || type.equals("mine") || type.equals("barrack") || type.equals("barrack")) {
            return new ResponseEntity(new Building(type), HttpStatus.OK);
        }
        else if (type == null || type.equals("")) {
            return new ResponseEntity(new ErrorResponseModel( "Missing parameter(s): type!"), HttpStatus.BAD_REQUEST);
        }
        else if ((type.equals("farm") || type.equals("mine") || type.equals("barrack") || type.equals("barrack"))) {
            return new ResponseEntity(new ErrorResponseModel("Invalid building type"), HttpStatus.NOT_ACCEPTABLE);
        }
        //not enough resources
        else if (false) {
            return new ResponseEntity(new ErrorResponseModel( "Not enough resources"), HttpStatus.CONFLICT);
        }
        else return new ResponseEntity(new ErrorResponseModel("Unexpected error"), HttpStatus.IM_USED);
    }

    @GetMapping(value = "/kingdom/buildings/{id}")
   @ResponseBody
    public ResponseEntity<Object> listTheBuilding( @PathVariable long id) {
        if (buildingRepo.findById(id).isPresent()) {
            return new ResponseEntity(buildingRepo.findById(id), HttpStatus.OK);
        } else return new ResponseEntity(new ErrorResponseModel( "Id not found"),
                HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/kingdom/buildings/{id}")
    public ResponseEntity<Object> upgradeOrDowngradeBuilding(@PathVariable long id,
                                                             @RequestBody BuildingInputJson buildingInputJson) {
        // and enough resources
        if (buildingRepo.findById(id).isPresent() && (true)) {
            buildingRepo.findById(id).get().setHP(buildingInputJson.getLevel());
            buildingRepo.save(buildingRepo.findById(id).get());
            return new ResponseEntity(buildingRepo.findById(id).get(), HttpStatus.OK);
        } else if ((buildingInputJson.getLevel() < 0 ) || buildingInputJson.getLevel() == null
                ||  buildingInputJson.getLevel().toString().isEmpty()) {
            return new ResponseEntity(new ErrorResponseModel( "Missing parameter(s): !"), HttpStatus.BAD_REQUEST);
        } else if (!(buildingRepo.findById(id).isPresent())) {
            return new ResponseEntity(new ErrorResponseModel( "Id not found"), HttpStatus.NOT_FOUND);
        }
        //not valid level of the building
        else if (false) {
            return new ResponseEntity(new ErrorResponseModel( "Invalid building level"), HttpStatus.NOT_ACCEPTABLE);
        }
        //not enough resource
        else if (false) {
            return new ResponseEntity(new ErrorResponseModel( "Not enough resource"), HttpStatus.CONFLICT);
        } else return new ResponseEntity(new ErrorResponseModel("Unexpected error"), HttpStatus.IM_USED);
    }
}
