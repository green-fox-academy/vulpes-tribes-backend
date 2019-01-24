package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.models.TroopList;
import com.tribesbackend.tribes.repositories.KingdomRepository;

import com.tribesbackend.tribes.services.troopservice.TroopModelHelpersMethods;
import com.tribesbackend.tribes.repositories.TroopRepository;
import com.tribesbackend.tribes.services.troopservice.TroopCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping(value = "/kingdom/troops")
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

    @GetMapping
    public ResponseEntity getTroops(){
        Optional<Kingdom> kingdom =  kingdomRepository.findKingdomByTribesUserUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return new ResponseEntity(new TroopList(kingdom.get().getTroops()), HttpStatus.OK);
    }
}
