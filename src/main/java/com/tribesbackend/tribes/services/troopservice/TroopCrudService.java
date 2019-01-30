package com.tribesbackend.tribes.services.troopservice;

import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.repositories.TroopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TroopCrudService {
    TroopRepository troopRepository;

    @Autowired
    public TroopCrudService (TroopRepository troopRepository) {this.troopRepository = troopRepository; }

    public  void save(Troop newTroop) throws Exception{
        troopRepository.save(newTroop);
    }
}
