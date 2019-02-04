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
    private ResourceRepository resourceRepository;

    @Autowired
    BuildingRestController(BuildingRepository buildingRepo, PurchaseService purchaseService, TimeService timeService,
                           ResourceRepository resourceRepository) {
        this.buildingRepo = buildingRepo;
        this.purchaseService = purchaseService;
        this.timeService = timeService;
        this.resourceRepository = resourceRepository;
    }

    @GetMapping(value = "/kingdom/buildings")
    public ResponseEntity getBuildings() {
        List<Building> updatedList = getCurrentKingdom().getBuildings();
        BuildingModelListResponseJson buildingModelListResponse = new BuildingModelListResponseJson();
        buildingModelListResponse.setBuildingList(updatedList);
        return new ResponseEntity(buildingModelListResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/kingdom/buildings")
    public ResponseEntity<Object> createBuilding(@RequestBody CreateBuildingJson createBuildingJson) {
        if (createBuildingJson.getType() == null || createBuildingJson.getType().equals("")) {
            return new ResponseEntity(new ErrorResponseModel("Missing parameter(s): type!"), HttpStatus.BAD_REQUEST);
        } else if (createBuildingJson.getType().equals("farm") || createBuildingJson.getType().equals("mine") || createBuildingJson.getType().equals("barracks") || createBuildingJson.getType().equals("townhall")) {
            Kingdom kingdom = getCurrentKingdom();
            if (purchaseService.purchasableItem(kingdom.getId(), createBuildingJson.getType(), 1)) {
                Building newBuilding = new Building(createBuildingJson.getType(), kingdom);
                newBuilding.setFinishedAt(timeService.finishedAtBuilding(newBuilding.getStartedAt(), createBuildingJson.getType(), 1));
                buildingRepo.save(newBuilding);
                return new ResponseEntity(newBuilding, HttpStatus.OK);
            } else return new ResponseEntity(new ErrorResponseModel("Not enough resources"), HttpStatus.CONFLICT);
        } else return new ResponseEntity(new ErrorResponseModel("Invalid building type"), HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping(value = "/kingdom/buildings/{id}")
    @ResponseBody
    public ResponseEntity<Object> listTheBuilding(@PathVariable long id) {
        if (buildingRepo.findById(id).isPresent()) {
            return new ResponseEntity(buildingRepo.findById(id), HttpStatus.OK);
        } else return new ResponseEntity(new ErrorResponseModel("Id not found"),
                HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/kingdom/buildings/{id}")
    public ResponseEntity<Object> upgradeOrDowngradeBuilding(@PathVariable Long id,
                                                             @RequestBody BuildingInputJson buildingInputJson) {
        //  long currentGoldAmount = resourceRepository.findByKingdom_IdAndType(getCurrentKingdom().getId(), "gold").get().getAmount();

        if (buildingRepo.findById(id).isPresent()
                && (buildingInputJson.getLevel() - buildingRepo.findById(id).get().getLevel() == 1)
                && (purchaseService.purchasableItem(id, "gold", buildingInputJson.getLevel()))
                && purchaseService.priceOfItem("gold", buildingInputJson.getLevel())
                <= purchaseService.currentGoldAmount(getCurrentKingdom().getId())) {
            buildingRepo.findById(id).get().setLevel(buildingInputJson.getLevel());
            purchaseService.decreaseGold(buildingInputJson.getLevel(),
                    getCurrentKingdom().getId(),
                    buildingRepo.findById(id).get().getType());
            buildingRepo.save(buildingRepo.findById(id).get());
            return new ResponseEntity(buildingRepo.findById(id).get(), HttpStatus.OK);
        } else if (buildingInputJson.getLevel() == null || buildingInputJson.getLevel().toString().isEmpty()) {
            return new ResponseEntity(new ErrorResponseModel("Missing parameter(s): level !"), HttpStatus.BAD_REQUEST);
        } else if (!(buildingRepo.findById(id).isPresent())) {
            return new ResponseEntity(new ErrorResponseModel("Id not found"), HttpStatus.NOT_FOUND);
        }
        //not valid level of the building
        else if ((buildingInputJson.getLevel() - buildingRepo.findById(id).get().getLevel() != 1) || buildingInputJson.getLevel() > 5) {
            return new ResponseEntity(new ErrorResponseModel("Invalid building level"), HttpStatus.NOT_ACCEPTABLE);
        }
        //not enough resource
        else if (purchaseService.priceOfItem("gold", buildingInputJson.getLevel())
                > purchaseService.currentGoldAmount(getCurrentKingdom().getId())) {
            return new ResponseEntity(new ErrorResponseModel("Not enough resource"), HttpStatus.CONFLICT);
        } else return new ResponseEntity(new ErrorResponseModel("This never can happen"), HttpStatus.IM_USED);
    }
}
