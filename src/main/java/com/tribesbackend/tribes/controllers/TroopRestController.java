
package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.models.TroopModelListResponse;
import com.tribesbackend.tribes.repositories.TroopRepository;
import com.tribesbackend.tribes.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping(value = "/kingdom/troops")

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
        return ResponseEntity.ok(new TroopModelListResponse(getCurrentKingdom().getTroops()));
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
