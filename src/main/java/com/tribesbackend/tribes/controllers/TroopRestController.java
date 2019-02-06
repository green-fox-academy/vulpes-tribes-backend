package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.models.TroopModelListResponse;
import com.tribesbackend.tribes.repositories.TroopRepository;
import com.tribesbackend.tribes.services.PurchaseService;
import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
import com.tribesbackend.tribes.services.troopservice.TroopCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TroopRestController extends BaseController {
    private TroopRepository troopRepository;
    private PurchaseService purchaseService;
    private TroopCrudService troopCrudService;

    @Autowired
    public TroopRestController(TroopRepository troopRepository, PurchaseService purchaseService, TroopCrudService troopCrudService) {
        this.troopRepository = troopRepository;
        this.purchaseService = purchaseService;
        this.troopCrudService = troopCrudService;
    }

    @GetMapping(value = "/kingdom/troops")
    public ResponseEntity getTroops() {
        List<Troop> updatedList = getCurrentKingdom().getTroops();
        TroopModelListResponse troopModelListResponse = new TroopModelListResponse();
        troopModelListResponse.setTroopList(updatedList);
        return new ResponseEntity(troopModelListResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/kingdom/troops")
    public ResponseEntity<Object> createTroop() {
        Kingdom kingdom = getCurrentKingdom();
        if (purchaseService.purchasableItem(kingdom.getId(), "troop", 1)==true){
            if(kingdom.getBuildings().contains("barrack")) {

//                kingdom.getBuildings().stream().filter(buildings -> buildings.getType().equals("barrack")).count();

                Troop newTroop = new Troop(kingdom);
                troopCrudService.createAndSaveTroop(kingdom);
                return new ResponseEntity(newTroop, HttpStatus.OK);
            } else return new ResponseEntity(new ErrorResponseModel("Not enough barrack"), HttpStatus.CONFLICT);
        }else return new ResponseEntity(new ErrorResponseModel("Not enough resources"), HttpStatus.CONFLICT);
    }
}
