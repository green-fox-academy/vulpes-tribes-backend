package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.jsonmodels.BuildingModelListResponseJson;
import com.tribesbackend.tribes.models.Building;
import com.tribesbackend.tribes.models.jsonmodels.BuildingInputJson;
import com.tribesbackend.tribes.models.jsonmodels.CreateBuildingJson;
import com.tribesbackend.tribes.repositories.BuildingRepository;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import com.tribesbackend.tribes.services.PurchaseService;
import com.tribesbackend.tribes.services.TimeService;
import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class BuildingRestController extends BaseController {
    private BuildingRepository buildingRepo;
    private PurchaseService purchaseService;
    private TimeService timeService;

    @Autowired
    BuildingRestController(BuildingRepository buildingRepo, PurchaseService purchaseService, TimeService timeService) {
        this.buildingRepo = buildingRepo;
        this.purchaseService = purchaseService;
        this.timeService = timeService;
    }

    @GetMapping(value = "/kingdom/buildings")
    public ResponseEntity getBuildings() {
        List<Building> updatedList = getCurrentKingdom().getBuildings();
        BuildingModelListResponseJson buildingModelListResponse = new BuildingModelListResponseJson();
        buildingModelListResponse.setBuildings(updatedList);
        return new ResponseEntity(buildingModelListResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/kingdom/buildings")
    public ResponseEntity<Object> createBuilding(@RequestBody CreateBuildingJson createBuildingJson) {
        if (createBuildingJson.getType() == null || createBuildingJson.getType().equals("")) {
            return ResponseEntity.status(400).body(new ErrorResponseModel("Missing parameter(s): type!"));
        } else if (createBuildingJson.getType().equals("farm") || createBuildingJson.getType().equals("mine") || createBuildingJson.getType().equals("barracks") || createBuildingJson.getType().equals("townhall")) {
            Kingdom kingdom = getCurrentKingdom();
            if (purchaseService.purchasableItem(kingdom.getId(), createBuildingJson.getType(), 1)) {
                Building newBuilding = new Building(createBuildingJson.getType(), kingdom);
                newBuilding.setFinishedAt(timeService.finishedAtBuilding(newBuilding.getStartedAt(), createBuildingJson.getType(), 1));
                buildingRepo.save(newBuilding);
                purchaseService.decreaseGold(1L,getCurrentKingdom().getId(),createBuildingJson.getType());
                return new ResponseEntity(newBuilding, HttpStatus.OK);
            } else return ResponseEntity.status(409).body(new ErrorResponseModel("Not enough resources"));
        } else return ResponseEntity.status(406).body(new ErrorResponseModel("Invalid building type"));
    }

    @GetMapping(value = "/kingdom/buildings/{id}")
    @ResponseBody
    public ResponseEntity<Object> listTheBuilding(@PathVariable long id) {
        if (buildingRepo.findById(id).isPresent()) {
            return new ResponseEntity(buildingRepo.findById(id), HttpStatus.OK);
        } else return ResponseEntity.status(404).body(new ErrorResponseModel("Id not found"));
    }

    @PutMapping(value = "/kingdom/buildings/{id}")
    public ResponseEntity<Object> upgradeOrDowngradeBuilding(@PathVariable Long id,
                                                             @RequestBody BuildingInputJson buildingInputJson) {
        if (buildingInputJson.getLevel() == null || buildingInputJson.getLevel().toString().isEmpty()) {
            return ResponseEntity.status(400).body(new ErrorResponseModel("Missing parameter(s): level !"));
            //not valid level of the building
        } else if (!(buildingRepo.findById(id).isPresent())) {
            return ResponseEntity.status(404).body(new ErrorResponseModel("Id not found"));
        } else if ((buildingInputJson.getLevel() - buildingRepo.findById(id).get().getLevel() != 1) || buildingInputJson.getLevel() > 5) {
            return ResponseEntity.status(406).body(new ErrorResponseModel("Invalid building level"));
        }else if (buildingRepo.findById(id).get().getFinishedAt()> System.currentTimeMillis()){
            return ResponseEntity.status(406).body(new ErrorResponseModel("Building is not created yet, cannot be updated"));
        } else if (buildingRepo.findById(id).isPresent()
                && (buildingInputJson.getLevel() - buildingRepo.findById(id).get().getLevel() == 1)
                && (purchaseService.purchasableItem(getCurrentKingdom().getId(), buildingRepo.findById(id).get().getType(), buildingInputJson.getLevel()))
               && purchaseService.purchasableItem(getCurrentKingdom().getId(), buildingRepo.findById(id).get().getType(), buildingInputJson.getLevel()))
            {
          Building updatedB = buildingRepo.findById(id).get();
                  updatedB.setLevel(buildingInputJson.getLevel());
                  updatedB.setStartedAt(System.currentTimeMillis());
                  updatedB.setFinishedAt(timeService.finishedAtBuilding(updatedB.getStartedAt(), updatedB.getType(), updatedB.getLevel()));
                  buildingRepo.save(updatedB);
            purchaseService.decreaseGold(buildingInputJson.getLevel(),getCurrentKingdom().getId(),buildingRepo.findById(id).get().getType());

            return new ResponseEntity(buildingRepo.findById(id).get(), HttpStatus.OK);

        }
        //not enough resource
        else if (purchaseService.priceOfItem(buildingRepo.findById(id).get().getType(), buildingInputJson.getLevel())
                > purchaseService.currentGoldAmount(getCurrentKingdom().getId())) {
            return ResponseEntity.status(409).body(new ErrorResponseModel("Not enough resource"));
        } else return ResponseEntity.status(226).body(new ErrorResponseModel("This never can happen"));
    }
}
