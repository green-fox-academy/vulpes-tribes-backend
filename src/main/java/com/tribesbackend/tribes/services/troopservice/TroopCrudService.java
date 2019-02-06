package com.tribesbackend.tribes.services.troopservice;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.repositories.TroopRepository;
import com.tribesbackend.tribes.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TroopCrudService {
    TroopRepository troopRepository;
    TimeService timeService;

    @Autowired
    public TroopCrudService (TroopRepository troopRepository, TimeService timeService) {
        this.troopRepository = troopRepository;
        this.timeService = timeService;
    }

    public  void save(Troop newTroop) throws Exception{
        troopRepository.save(newTroop);
    }

    public Troop createAndSaveTroop(Kingdom kingdom){
        Troop newTroop = new Troop(kingdom);
//        newTroop.setFinishedAt(timeService.finishedAtTroop(newTroop.getStartedAt(),1, buildingRepository.findByType("barrack").get().getLevel()));
        troopRepository.save(newTroop);
        return newTroop;
    }
}
