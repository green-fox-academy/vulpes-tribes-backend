
package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Building;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.models.jsonmodels.TroopModelListResponseJson;
import com.tribesbackend.tribes.models.jsonmodels.TroopSoloIdJson;
import com.tribesbackend.tribes.repositories.BuildingRepository;
import com.tribesbackend.tribes.repositories.TroopRepository;
import com.tribesbackend.tribes.services.PurchaseService;
import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
import com.tribesbackend.tribes.services.troopservice.TroopCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class TroopRestController extends BaseController {
    private TroopRepository troopRepository;
    private PurchaseService purchaseService;
    private TroopCrudService troopCrudService;
    private BuildingRepository buildingRepository;


    @Autowired
    public TroopRestController(TroopRepository troopRepository, PurchaseService purchaseService, TroopCrudService troopCrudService, BuildingRepository buildingRepository) {
        this.troopRepository = troopRepository;
        this.purchaseService = purchaseService;
        this.troopCrudService = troopCrudService;
        this.buildingRepository = buildingRepository;
    }

    @GetMapping(value = "/kingdom/troops")
    public ResponseEntity getTroops() {
        return ResponseEntity.ok(new TroopModelListResponseJson(getCurrentKingdom().getTroops()));
    }

    @PostMapping(value = "/kingdom/troops")
    public ResponseEntity<Object> createTroop() {
        Kingdom kingdom = getCurrentKingdom();
        List<Building> barrackList = buildingRepository.findByKingdomIdAndType(kingdom.getId(), "barracks");
        if (purchaseService.purchasableItem(kingdom.getId(), "troop", 1)) {
            if (troopCrudService.barrackIsAvaliable(kingdom, barrackList)) {
                Troop newTroop = troopCrudService.createAndSaveTroop(kingdom, barrackList);
                return new ResponseEntity(newTroop, HttpStatus.OK);
            } else return ResponseEntity.status(409).body(new ErrorResponseModel("No barracks available"));
        } else return ResponseEntity.status(409).body(new ErrorResponseModel("Not enough resources"));
    }

    @GetMapping(value = "/kingdom/troops/{id}")
    public ResponseEntity<Object> listOfTroops(@PathVariable long id) {
        if (troopRepository.findById(id).isPresent()) {
            return new ResponseEntity(troopRepository.findById(id), HttpStatus.OK);
        } else return ResponseEntity.status(200).body(new ErrorResponseModel("Id not found"));
    }

    @PutMapping(value = "/kingdom/troops/{id}")
    public ResponseEntity<Object> upgradeTroopLevel(@PathVariable Long id, @RequestBody TroopSoloIdJson troopSoloIdJson) {
        Kingdom kingdom = getCurrentKingdom();
        Troop troop = troopRepository.findById(id).get();
        if (!troopRepository.findById(id).isPresent()) {
            return ResponseEntity.status(400).body(new ErrorResponseModel("Id not found."));
        }
        if (troopSoloIdJson == null) {
            return ResponseEntity.status(400).body(new ErrorResponseModel("Missing parameter/(s)!"));
        }
        if (troopSoloIdJson.getLevel() - 1 != troop.getLevel()) {
            return ResponseEntity.status(406).body(new ErrorResponseModel("Invalid troop level."));
        }
        if (!purchaseService.purchasableItem(kingdom.getId(), "troop", troop.getLevel() + 1)) {
            return ResponseEntity.status(409).body(new ErrorResponseModel("Not enough resource."));
        }
        if (troop.getFinishedAt() <= System.currentTimeMillis()) {
            return ResponseEntity.status(406).body(new ErrorResponseModel("Troop is still creating."));
        }
        if (troopCrudService.barrackIsAvaliable(kingdom, buildingRepository.findByKingdomIdAndType(kingdom.getId(), "barracks"))) {
            return ResponseEntity.status(200).body(troopCrudService.updateTroopLevel(kingdom,troop,troopSoloIdJson));
        }
        else return ResponseEntity.status(409).body(new ErrorResponseModel("No barracks available."));
    }
}
