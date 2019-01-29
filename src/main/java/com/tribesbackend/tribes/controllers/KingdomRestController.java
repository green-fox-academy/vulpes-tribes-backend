package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Location;
import com.tribesbackend.tribes.models.jsonmodels.KingdomInputJson;
import com.tribesbackend.tribes.models.jsonmodels.KingdomResponseJson;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KingdomRestController extends BaseController {

    @Autowired
    public KingdomRestController(KingdomRepository kingdomRepository) {
        this.kingdomRepository = kingdomRepository;
    }

    @GetMapping(value = "/kingdom")
    public ResponseEntity getKingdom() {
        return new ResponseEntity(getCurrentKingdom(), HttpStatus.OK);
    }

    @PutMapping(value = "/kingdom")
    public ResponseEntity putKingdom(@RequestBody KingdomInputJson kingdomInputJson) {
        Kingdom kingdom = getCurrentKingdom();
        kingdom.setName(kingdomInputJson.getName());
        kingdom.setLocation(new Location(kingdomInputJson.getLocationX(), kingdomInputJson.getLocationY()));
        kingdomRepository.save(kingdom);
        return new ResponseEntity(new KingdomResponseJson(kingdom), HttpStatus.OK);
    }

    @GetMapping(value = "/ki ngdom/{id}")
    public ResponseEntity getKingdomId(@PathVariable Long id) {
        if (kingdomRepository.findKingdomById(id).get() == null || !kingdomRepository.findKingdomById(id).isPresent()) {
            return new ResponseEntity(new ErrorResponseModel("Id not found"), HttpStatus.NOT_FOUND);
        } else {

            Kingdom kingdom = kingdomRepository.findKingdomById(id).get();
            return new ResponseEntity(new KingdomResponseJson(kingdom), HttpStatus.OK);
        }
    }
}