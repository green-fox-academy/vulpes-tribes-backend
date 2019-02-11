package com.tribesbackend.tribes.services.troopservice;

import com.tribesbackend.tribes.models.Building;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.models.jsonmodels.TroopSoloIdJson;
import com.tribesbackend.tribes.repositories.BuildingRepository;
import com.tribesbackend.tribes.repositories.TroopRepository;
import com.tribesbackend.tribes.services.PurchaseService;
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
    private PurchaseService purchaseService;

    @Autowired
    public TroopCrudService(TroopRepository troopRepository, TimeService timeService, BuildingRepository buildingRepository, PurchaseService purchaseService) {
        this.troopRepository = troopRepository;
        this.timeService = timeService;
        this.buildingRepository = buildingRepository;
        this.purchaseService = purchaseService;
    }

    public void save(Troop newTroop) throws Exception {
        troopRepository.save(newTroop);
    }


    //        newTroop.setFinishedAt(timeService.finishedAtTroop(newTroop.getStartedAt(),1, buildingRepository.findByType("barrack").get().getLevel()));
//        (newBuilding.getStartedAt(), createBuildingJson.getType(), 1
//        .stream().filter(buildings -> buildings.getType().equals("barrack"))

    public Troop createAndSaveTroop(Kingdom kingdom, List<Building> barrackList) {
        Troop newTroop = new Troop(1, 100, 50, 20);
        newTroop.setKingdom(kingdom);
        int maxLevel = barrackList.stream().max(Comparator.comparing(Building::getLevel)).get().getLevel();
        newTroop.setFinishedAt(timeService.finishedAtTroop(newTroop.getStartedAt(), 1, maxLevel));
        purchaseService.decreaseGold(1, kingdom.getId(), "troop");
        troopRepository.save(newTroop);
        return newTroop;
    }

    public boolean barrackIsAvaliable(Kingdom kingdom, List<Building> barrackList) {
        List<Troop> troopList = troopRepository.findAllByKingdom(kingdom);
        int counterOfBuildingTroops = 0;


        for (Troop troop : troopList
        ) {
            if (troop.getFinishedAt() > (System.currentTimeMillis())) {
                counterOfBuildingTroops++;
            }
        }
        return (counterOfBuildingTroops < barrackList.size());
    }

    public Troop updateTroopLevel(Kingdom kingdom, Troop troop, TroopSoloIdJson troopSoloIdJson){
        int updatedLevel = troop.getLevel() + 1;
        List<Building> barrackList = buildingRepository.findByKingdomIdAndType(kingdom.getId(), "barracks");
        troop.setLevel(updatedLevel);
        troop.setStartedAt(System.currentTimeMillis());
        troop.setFinishedAt(timeService.finishedAtTroop(troop.getStartedAt(), troop.getLevel(), barrackList.stream().max(Comparator.comparing(Building::getLevel)).get().getLevel()));
        troopRepository.save(troop);
        purchaseService.decreaseGold(troopSoloIdJson.getLevel(), kingdom.getId(), "troop");
        return troop;
    }
}
