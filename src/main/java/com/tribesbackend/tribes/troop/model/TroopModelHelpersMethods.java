package com.tribesbackend.tribes.troop.model;

import com.tribesbackend.tribes.troop.repository.TroopRepository;
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
