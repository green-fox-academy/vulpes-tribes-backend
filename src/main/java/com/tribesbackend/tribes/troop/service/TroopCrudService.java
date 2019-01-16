package com.tribesbackend.tribes.troop.service;

import com.tribesbackend.tribes.troop.model.Troop;
import com.tribesbackend.tribes.troop.repository.TroopRepository;
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
