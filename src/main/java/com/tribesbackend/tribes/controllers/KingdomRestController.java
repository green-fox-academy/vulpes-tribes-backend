package com.tribesbackend.tribes.controllers;


import com.tribesbackend.tribes.repositories.KingdomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KingdomRestController {

    KingdomRepository kingdomRepository;

    @Autowired
    public KingdomRestController (KingdomRepository kingdomRepository){
        this.kingdomRepository = kingdomRepository;
    }

    @GetMapping(value = "/kingdom")
    public ResponseEntity getKingdom() {
        return new ResponseEntity(kingdomRepository.findKingdomByTribesUserUsername(SecurityContextHolder.getContext().getAuthentication().getName()), HttpStatus.OK);
    }
}
