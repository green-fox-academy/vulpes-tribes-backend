package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.jsonmodels.PlayerListJson;
import com.tribesbackend.tribes.models.jsonmodels.RegistrationResponseJson;
import com.tribesbackend.tribes.models.jsonmodels.TroopIdsJson;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.repositories.UserTRepository;
import com.tribesbackend.tribes.services.BattleService;
import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
import com.tribesbackend.tribes.services.responseservice.OKstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MockBattleRestController extends BaseController{

    private KingdomRepository kingdomRepository;
    private BattleService battleService;
    @Autowired
    public MockBattleRestController(KingdomRepository kingdomRepository, BattleService battleService) {
        this.kingdomRepository = kingdomRepository;
        this.battleService = battleService;
    }

    @GetMapping(value = "/players")
    public ResponseEntity getPlayerList() {
            return new ResponseEntity(new PlayerListJson(battleService.otherPlayers(getCurrentUser().getUsername())), HttpStatus.OK);
    }

        @PostMapping(value = "/kingdom/{id}/battle")
    public ResponseEntity startBattle(@PathVariable("id") Long id, @RequestBody TroopIdsJson troopIdsJson) {
        Optional<Kingdom> enemyKingdom = kingdomRepository.findKingdomById(id);
        if (enemyKingdom.isPresent()) {
            return new ResponseEntity(new OKstatus("Battle started against" + enemyKingdom.get().getName()), HttpStatus.OK);
        }
        else return new ResponseEntity(new ErrorResponseModel("No such kingdom"), HttpStatus.CONFLICT);
    }
}
