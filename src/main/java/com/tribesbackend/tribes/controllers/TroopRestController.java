
package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.models.jsonmodels.TroopModelListResponseJson;
import com.tribesbackend.tribes.repositories.BuildingRepository;
import com.tribesbackend.tribes.repositories.TroopRepository;
import com.tribesbackend.tribes.services.PurchaseService;
import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
import com.tribesbackend.tribes.services.troopservice.TroopCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (purchaseService.purchasableItem(kingdom.getId(), "troop", 1)==true){
            if(!buildingRepository.findByKingdomIdAndType(kingdom.getId(),"barracks").isEmpty()) {
                Troop azetroop = troopCrudService.createAndSaveTroop(kingdom);
                return new ResponseEntity( azetroop, HttpStatus.OK);
            } else return new ResponseEntity(new ErrorResponseModel("Not enough barrack"), HttpStatus.CONFLICT);
        }else return new ResponseEntity(new ErrorResponseModel("Not enough resources"), HttpStatus.CONFLICT);
    }
}
