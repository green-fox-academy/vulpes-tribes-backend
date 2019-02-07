
package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Building;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.models.jsonmodels.TroopModelListResponseJson;
import com.tribesbackend.tribes.models.jsonmodels.TroopSoloIdJson;
import com.tribesbackend.tribes.repositories.BuildingRepository;
import com.tribesbackend.tribes.repositories.TroopRepository;
import com.tribesbackend.tribes.services.PurchaseService;
import com.tribesbackend.tribes.services.TimeService;
import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
import com.tribesbackend.tribes.services.troopservice.TroopCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;

@CrossOrigin("*")
@RestController
public class TroopRestController extends BaseController {
    private TroopRepository troopRepository;
    private PurchaseService purchaseService;
    private TroopCrudService troopCrudService;
    private BuildingRepository buildingRepository;
    private TimeService timeService;

    @Autowired
    public TroopRestController(TroopRepository troopRepository, PurchaseService purchaseService, TroopCrudService troopCrudService, BuildingRepository buildingRepository, TimeService timeService) {
        this.troopRepository = troopRepository;
        this.purchaseService = purchaseService;
        this.troopCrudService = troopCrudService;
        this.buildingRepository = buildingRepository;
        this.timeService = timeService;
    }

    @GetMapping(value = "/kingdom/troops")
    public ResponseEntity getTroops() {
        return ResponseEntity.ok(new TroopModelListResponseJson(getCurrentKingdom().getTroops()));
    }

    @PostMapping(value = "/kingdom/troops")
    public ResponseEntity<Object> createTroop() {
        Kingdom kingdom = getCurrentKingdom();
        List<Building> barrackList = buildingRepository.findByKingdomIdAndType(kingdom.getId(), "barracks");
        if (purchaseService.purchasableItem(kingdom.getId(), "troop", 1) == true) {
            if (troopCrudService.barrackIsAvaliable(kingdom, barrackList)) {
                Troop newTroop = troopCrudService.createAndSaveTroop(kingdom, barrackList);
                return new ResponseEntity(newTroop, HttpStatus.OK);
            } else return new ResponseEntity(new ErrorResponseModel("No barracks available"), HttpStatus.CONFLICT);
        } else return new ResponseEntity(new ErrorResponseModel("Not enough resources"), HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/kingdom/troops/{id}")
    public ResponseEntity<Object> listOfTroops(@PathVariable long id) {
        if (troopRepository.findById(id).isPresent()) {
            return new ResponseEntity(troopRepository.findById(id), HttpStatus.OK);
        } else return new ResponseEntity(new ErrorResponseModel("Id not found"),
                HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/kingdom/troops/{id}")
    public ResponseEntity<Object> upgradeTroopLevel(@PathVariable Long id, @RequestBody TroopSoloIdJson troopSoloIdJson) {
        Kingdom kingdom = getCurrentKingdom();
        if (troopRepository.findById(id).isPresent()) {
            Troop troop = troopRepository.findById(id).get();
            if (troop.getId() == 0 || troop.getLevel() == 0 || troop.getHp() == 0 || troop.getAttack() == 0 || troop.getDefence() == 0 || troop.getStartedAt() == 0 || troop.getFinishedAt() == 0) {
                if (troopSoloIdJson.getLevel() - 1 == troop.getLevel()) {
                    if (purchaseService.purchasableItem(kingdom.getId(), "troop", troop.getLevel() + 1) == true) {
                        if (troop.getFinishedAt() <= System.currentTimeMillis()) {
                            if (troopCrudService.barrackIsAvaliable(kingdom, buildingRepository.findByKingdomIdAndType(kingdom.getId(), "barracks")) == true) {
                                int updatedLevel = troop.getLevel() + 1;
                                List<Building> barrackList = buildingRepository.findByKingdomIdAndType(kingdom.getId(), "barracks");
                                troop.setLevel(updatedLevel);
                                troop.setStartedAt(System.currentTimeMillis());
                                troop.setFinishedAt(timeService.finishedAtTroop(troop.getStartedAt(), troop.getLevel(), barrackList.stream().max(Comparator.comparing(Building::getLevel)).get().getLevel()));
                                troopRepository.save(troop);
                                purchaseService.decreaseGold(troopSoloIdJson.getLevel(), kingdom.getId(), "troop");
                                return new ResponseEntity(troop, HttpStatus.OK);
                            } else return new ResponseEntity(new ErrorResponseModel("No barracks available."), HttpStatus.CONFLICT);
                        } else return new ResponseEntity(new ErrorResponseModel("Troop is still creating."), HttpStatus.NOT_ACCEPTABLE);
                    } else return new ResponseEntity(new ErrorResponseModel("Not enough resource."), HttpStatus.CONFLICT);
                } else return new ResponseEntity(new ErrorResponseModel("Invalid troop level."), HttpStatus.NOT_ACCEPTABLE);
            } else return new ResponseEntity(new ErrorResponseModel("Missing parameter/(s)!"), HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity(new ErrorResponseModel("Id not found."), HttpStatus.NOT_FOUND);
    }
}
