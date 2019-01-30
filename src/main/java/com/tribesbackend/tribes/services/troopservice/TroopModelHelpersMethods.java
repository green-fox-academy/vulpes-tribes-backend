package com.tribesbackend.tribes.services.troopservice;

import com.tribesbackend.tribes.repositories.TroopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TroopModelHelpersMethods {
    TroopRepository troopRepository;

    @Autowired
    public  TroopModelHelpersMethods (TroopRepository troopRepository) {
        this.troopRepository = troopRepository;
    }
}
