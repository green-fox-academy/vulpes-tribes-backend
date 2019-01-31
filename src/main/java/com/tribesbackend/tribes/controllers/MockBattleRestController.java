package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.models.jsonmodels.PlayerListJson;
import com.tribesbackend.tribes.models.jsonmodels.RegistrationResponseJson;
import com.tribesbackend.tribes.models.jsonmodels.TroopIdsJson;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.repositories.UserTRepository;
import com.tribesbackend.tribes.services.responseservice.ErrorResponseModel;
import com.tribesbackend.tribes.services.responseservice.OKstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MockBattleRestController extends BaseController{

    private UserTRepository userTRepository;
    private KingdomRepository kingdomRepository;
    @Autowired
    public MockBattleRestController(UserTRepository userTRepository, KingdomRepository kingdomRepository) {
        this.userTRepository = userTRepository;
        this.kingdomRepository = kingdomRepository;
    }

    @GetMapping(value = "/players")
    public ResponseEntity getPlayerList() {
        List<TribesUser> users = userTRepository.findAll();
        List<RegistrationResponseJson> players = new ArrayList<>();
        for (TribesUser user:
             users) {
            players.add(new RegistrationResponseJson(user.getId(), user.getUsername(), user.getKingdom().getId(), "No avatar yet", 0));
        }
        return new ResponseEntity(new PlayerListJson(players), HttpStatus.OK);
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
