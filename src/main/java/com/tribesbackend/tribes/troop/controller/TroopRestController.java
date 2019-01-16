package com.tribesbackend.tribes.troop.controller;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribeskingdom.repository.KingdomRepository;
import com.tribesbackend.tribes.tribesuser.errorservice.ErrorMessagesMethods;
import com.tribesbackend.tribes.troop.model.TroopModelHelpersMethods;
import com.tribesbackend.tribes.troop.repository.TroopRepository;
import com.tribesbackend.tribes.troop.service.TroopCrudService;
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
    ErrorMessagesMethods errorMessages;
    TroopCrudService troopCrudService;

    @Autowired
    public TroopRestController (TroopRepository troopRepository, KingdomRepository kingdomRepository, TroopModelHelpersMethods troopModelHelpersMethods, ErrorMessagesMethods errorMessages, TroopCrudService troopCrudService ){
        this.troopRepository = troopRepository;
        this.kingdomRepository = kingdomRepository;
        this.troopModelHelpersMethods = troopModelHelpersMethods;
        this.errorMessages = errorMessages;
        this.troopCrudService = troopCrudService;
    }

    @GetMapping(value = "/kingdom/troops")
    public ResponseEntity getBuilding(@RequestHeader(name = "username")String username){
        Kingdom selectedKingdom =  kingdomRepository.findKingdomByTribesUserUsername(username);
        return new ResponseEntity(troopRepository.findAllByKingdom(selectedKingdom), HttpStatus.OK);
    }
}
