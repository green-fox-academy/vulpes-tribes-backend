package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class BaseController {
    KingdomRepository kingdomRepository;

    @Autowired
    public void setKingdomRepository(KingdomRepository kingdomRepository) {
        this.kingdomRepository = kingdomRepository;
    }


    public Kingdom getCurrentKingdom() {
        Kingdom currentKingdom = kingdomRepository.findKingdomByTribesUserUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName()).get();
        return currentKingdom;
    }
}
