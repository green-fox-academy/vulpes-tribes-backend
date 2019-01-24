package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.repositories.UserTRepository;
import com.tribesbackend.tribes.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

 //   BuildingRepository buildingRepo;
    KingdomRepository kingdomRepository;
    UserTRepository userTRepository;

    @Autowired
    BaseController (KingdomRepository kingdomRepository, UserTRepository userTRepository){
    this.kingdomRepository = kingdomRepository;
    this.userTRepository = userTRepository;
    }


    public Kingdom getCurrentKingdom(String jwtToken){
      //  String token = request.getHeader(HEADER_STRING);
        Kingdom currentKingdom = kingdomRepository.findKingdomByTribesUser(JWTService.extractUsername(jwtToken));
        return currentKingdom;
    }
}
