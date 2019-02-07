
package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.models.jsonmodels.BuildingInputJson;
import com.tribesbackend.tribes.models.jsonmodels.TroopModelListResponseJson;
import com.tribesbackend.tribes.repositories.TroopRepository;
import com.tribesbackend.tribes.services.PurchaseService;
import com.tribesbackend.tribes.services.TimeService;
import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
public class TroopRestController extends BaseController {
    private TroopRepository troopRepository;
    private PurchaseService purchaseService;
    private TimeService timeService;

    @Autowired
    public TroopRestController(TroopRepository troopRepository, PurchaseService purchaseService, TimeService timeService) {
        this.troopRepository = troopRepository;
        this.purchaseService = purchaseService;
        this.timeService = timeService;
    }

    @GetMapping(value = "/kingdom/troops")
    public ResponseEntity getTroops() {
        return ResponseEntity.ok(new TroopModelListResponseJson(getCurrentKingdom().getTroops()));
    }

//    @PostMapping(value = "/kingdom/troops")
//    public ResponseEntity createTroop() {
//        Kingdom kingdom = getCurrentKingdom();
//        if (purchaseService.purchasableItem(kingdom.getId(), "troop",1)){
//            Troop newTroop = new Troop();
//            troopRepository.save(newTroop);
//        }
//    }


    @PutMapping(value = "/kingdom/troops/{id}")
    public ResponseEntity<Object> upgradeOrDowngradeTroop(@PathVariable Long id,
                                                          @RequestBody BuildingInputJson buildingInputJson) {
        Optional<Troop> troop = troopRepository.findById(id);

        if (buildingInputJson.getLevel() == null || buildingInputJson.getLevel().toString().isEmpty()) {
            return ResponseEntity.status(400).body(new ErrorResponseModel("Missing parameter(s): level !"));
            //not valid level of the building
        } else if (!(troop.isPresent())) {
            return ResponseEntity.status(404).body(new ErrorResponseModel("Id not found"));
        } else if ((buildingInputJson.getLevel() - troop.get().getLevel() != 1) || buildingInputJson.getLevel() < 0 || buildingInputJson.getLevel() > 5) {
            return ResponseEntity.status(406).body(new ErrorResponseModel("Invalid troop level"));
        } else if (troop.get().getFinishedAt() > System.currentTimeMillis()) {
            return ResponseEntity.status(406).body(new ErrorResponseModel("Building is not created yet, cannot be updated"));
        } else if (!purchaseService.purchasableTroop(getCurrentKingdom(), buildingInputJson.getLevel())) {
            return ResponseEntity.status(409).body(new ErrorResponseModel("Not enough resource"));
        } else {
            troop.get().setLevel(buildingInputJson.getLevel());
            troop.get().setStartedAt(System.currentTimeMillis());
            troop.get().setFinishedAt(timeService.finishedAtBuilding(troop.get().getStartedAt(), "troop", troop.get().getLevel()));
            troopRepository.save(troop.get());
            purchaseService.decreaseGold(buildingInputJson.getLevel(), getCurrentKingdom().getId(), "troop");
            return ResponseEntity.status(200).body(troop.get());
        }
    }
}