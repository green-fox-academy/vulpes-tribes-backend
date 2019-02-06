package com.tribesbackend.tribes.services.troopservice;

import com.tribesbackend.tribes.models.Building;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.repositories.BuildingRepository;
import com.tribesbackend.tribes.repositories.TroopRepository;
import com.tribesbackend.tribes.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


@Service
public class TroopCrudService {
    TroopRepository troopRepository;
    private TimeService timeService;
    private BuildingRepository buildingRepository;

    @Autowired
    public TroopCrudService(TroopRepository troopRepository, TimeService timeService, BuildingRepository buildingRepository) {this.troopRepository = troopRepository;
        this.timeService = timeService;
        this.buildingRepository = buildingRepository;
    }

    public  void save(Troop newTroop) throws Exception{
        troopRepository.save(newTroop);
    }


    //        newTroop.setFinishedAt(timeService.finishedAtTroop(newTroop.getStartedAt(),1, buildingRepository.findByType("barrack").get().getLevel()));
//        (newBuilding.getStartedAt(), createBuildingJson.getType(), 1
//        .stream().filter(buildings -> buildings.getType().equals("barrack"))

    public Troop createAndSaveTroop(Kingdom kingdom){
        Troop newTroop = new Troop(kingdom);

        List<Building> listOfBarracks = buildingRepository.findByKingdomIdAndType(kingdom.getId(),"barracks");
        listOfBarracks.stream().max(Comparator.comparing(Building::getLevel));


        kingdom.getBuildings().stream().filter(buildings -> buildings.getType().equals("barrack"));
        newTroop.setFinishedAt(timeService.finishedAtTroop(newTroop.getStartedAt(),1,   listOfBarracks.stream().max(Comparator.comparing(Building::getLevel)).get().getLevel()));
    troopRepository.save(newTroop);
        return newTroop;
    }
}
