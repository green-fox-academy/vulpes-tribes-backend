package com.tribesbackend.tribes.tribeskingdom.controllers;

import com.tribesbackend.tribes.tribeskingdom.repository.KingdomRepository;
import com.tribesbackend.tribes.tribeskingdom.service.KingdomCrudService;
import com.tribesbackend.tribes.tribesuser.okstatusservice.OKstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KingdomRestController {

    KingdomRepository kingdomRepository;
    KingdomCrudService kingdomCrudService;

    @Autowired
    public KingdomRestController (KingdomRepository kingdomRepository,   KingdomCrudService kingdomCrudService ){
        this.kingdomRepository = kingdomRepository;
        this.kingdomCrudService = kingdomCrudService;
    }

    @GetMapping(value = "/kingdom")
    public ResponseEntity getKingdom(@RequestHeader(name = "username") String username) {
        return new ResponseEntity(new OKstatus("ok", kingdomRepository.findKingdomByTribesUserUsername(username)), HttpStatus.OK);
    }
}
