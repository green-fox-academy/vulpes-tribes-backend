package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.services.KingdomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    KingdomService kingdomService;
    KingdomRepository kingdomRepository;

    @Autowired
    public void setKingdomRepository(KingdomRepository kingdomRepository) {
        this.kingdomRepository = kingdomRepository;
    }

    @Autowired
    public void setKingdomService(KingdomService kingdomService) {
        this.kingdomService = kingdomService;
    }

    public Kingdom getCurrentKingdom() {
        return kingdomService.verifyKingdom(SecurityContextHolder.getContext()
                .getAuthentication().getName());
    }
}
