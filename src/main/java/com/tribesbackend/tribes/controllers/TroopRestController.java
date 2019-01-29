package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;

import com.tribesbackend.tribes.services.troopservice.TroopModelHelpersMethods;
import com.tribesbackend.tribes.repositories.TroopRepository;
import com.tribesbackend.tribes.services.troopservice.TroopCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TroopRestController {
    TroopRepository troopRepository;
    KingdomRepository kingdomRepository;
    TroopModelHelpersMethods troopModelHelpersMethods;
    TroopCrudService troopCrudService;

    @Autowired
    public TroopRestController (TroopRepository troopRepository, KingdomRepository kingdomRepository, TroopModelHelpersMethods troopModelHelpersMethods, TroopCrudService troopCrudService ){
        this.troopRepository = troopRepository;
        this.kingdomRepository = kingdomRepository;
        this.troopModelHelpersMethods = troopModelHelpersMethods;
        this.troopCrudService = troopCrudService;
    }

    @GetMapping(value = "/kingdom/troops")
    public ResponseEntity getBuilding(@RequestHeader(name = "username")String username){
        Kingdom selectedKingdom =  kingdomRepository.findKingdomByTribesUserUsername(username);
        return new ResponseEntity(troopRepository.findAllByKingdom(selectedKingdom).get(), HttpStatus.OK);
    }
}
