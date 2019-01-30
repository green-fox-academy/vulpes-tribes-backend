package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.models.TroopModelListResponse;
import com.tribesbackend.tribes.repositories.TroopRepository;
import com.tribesbackend.tribes.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TroopRestController extends BaseController {
    private TroopRepository troopRepository;
    private PurchaseService purchaseService;

    @Autowired
    public TroopRestController(TroopRepository troopRepository, PurchaseService purchaseService) {
        this.troopRepository = troopRepository;
        this.purchaseService = purchaseService;
    }

    @GetMapping(value = "/kingdom/troops")
    public ResponseEntity getTroops() {
        List<Troop> updatedList = getCurrentKingdom().getTroops();
        TroopModelListResponse troopModelListResponse = new TroopModelListResponse();
        troopModelListResponse.setTroopList(updatedList);
        return new ResponseEntity(troopModelListResponse, HttpStatus.OK);
    }

//    @PostMapping(value = "/kingdom/troops")
//    public ResponseEntity createTroop() {
//        Kingdom kingdom = getCurrentKingdom();
//        if (purchaseService.purchasableItem(kingdom.getId(), "troop",1)){
//            Troop newTroop = new Troop();
//            troopRepository.save(newTroop);
//        }
//    }
}
