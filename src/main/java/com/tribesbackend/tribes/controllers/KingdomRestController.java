package com.tribesbackend.tribes.controllers;


import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.services.responseservice.OKstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KingdomRestController {

    KingdomRepository kingdomRepository;

    @Autowired
    public KingdomRestController (KingdomRepository kingdomRepository){
        this.kingdomRepository = kingdomRepository;
    }

    @GetMapping(value = "/kingdom")
    public ResponseEntity getKingdom(@RequestHeader(name = "username") String username) {
        return new ResponseEntity(kingdomRepository.findKingdomByTribesUserUsername(username), HttpStatus.OK);
    }
}