package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.TroopList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "/kingdom/troops")
@RestController
public class TroopRestController extends BaseController {

    @Autowired
    public TroopRestController() {
    }

    @GetMapping
    public ResponseEntity getTroops() {
        Kingdom kingdom = getCurrentKingdom();
        return new ResponseEntity(new TroopList(kingdom.getTroops()), HttpStatus.OK);
    }
}
