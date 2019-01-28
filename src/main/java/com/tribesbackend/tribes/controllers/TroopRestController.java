package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.repositories.TroopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TroopRestController extends BaseController {
    TroopRepository troopRepository;

    @Autowired
    TroopRestController(TroopRepository troopRepository) {
        this.troopRepository = troopRepository;
    }

    @GetMapping(value = "/kingdom/troops")
    public ResponseEntity getBuilding() {

        return new ResponseEntity(troopRepository.findAllByKingdom(getCurrentKingdom()), HttpStatus.OK);
    }
}
