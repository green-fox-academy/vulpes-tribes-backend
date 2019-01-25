package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    KingdomRepository kingdomRepository;
//    UserTRepository userTRepository;

    @Autowired
    public void setKingdomRepository (KingdomRepository kingdomRepository){
    this.kingdomRepository = kingdomRepository;
 //   this.userTRepository = userTRepository;
    }

    public Kingdom getCurrentKingdom(){
        Kingdom currentKingdom =  kingdomRepository.findKingdomByTribesUserUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName()).get();;
        return currentKingdom;
    }
}
