package com.tribesbackend.tribes.services;

import com.tribesbackend.tribes.models.Building;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.ResourcesModel;
import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.repositories.BuildingRepository;
import com.tribesbackend.tribes.repositories.ItemPriceRepository;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import com.tribesbackend.tribes.repositories.TroopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ItemPriceRepository itemPriceRepository;
    @Autowired
    BuildingRepository buildingRepo;
    @Autowired
    TroopRepository troopRepo;


    public long priceOfItem(String type, long level) {
        if (level >= 1 && level <= 5 && itemPriceRepository.findByType(type).isPresent()) {
            return itemPriceRepository.findByType(type).get().getGold() * level;
        } else throw new IllegalArgumentException();
    }

    public long currentGoldAmount(long kingdomId) {
        return resourceRepository.findByKingdom_IdAndType(kingdomId, "gold").get().getAmount();
    }

    public boolean purchasableItem(Long kingdom_id, String itemType, int desiredLevel) {
        if (resourceRepository.findByKingdom_IdAndType(kingdom_id, "gold").isPresent()) {
            return currentGoldAmount(kingdom_id) >= priceOfItem(itemType, desiredLevel);
        } else throw new IllegalArgumentException();
    }

    public boolean purchasableBuilding (Kingdom kingdom, long buildingId) {
        if (resourceRepository.findByKingdom_IdAndType(kingdom.getId(), "gold").isPresent()) {
           Optional<Building>  building = buildingRepo.findById(buildingId);
            return currentGoldAmount(kingdom.getId()) >= priceOfItem(building.get().getType(),building.get().getLevel()+1);
        } else throw new IllegalArgumentException();
    }

    public  boolean purchasableTroop (Kingdom kingdom, long troopId){
        if (resourceRepository.findByKingdom_IdAndType(kingdom.getId(), "gold").isPresent()) {
            Optional<Troop>  troop = troopRepo.findById(troopId);
            return currentGoldAmount(kingdom.getId()) >= priceOfItem("troop",troop.get().getLevel()+1);
        } else throw new IllegalArgumentException();
    }

    public void decreaseGold(long desiredBuildingLevel, long kingdomId, String itemType) {

        if (resourceRepository.findByKingdom_IdAndType(kingdomId, "gold").get().getAmount() > 0) {
            ResourcesModel updated = resourceRepository.findByKingdom_IdAndType(kingdomId, "gold").get();
            updated.setAmount(currentGoldAmount(kingdomId) - priceOfItem(itemType, desiredBuildingLevel));
            resourceRepository.save(updated);
        } else throw new IllegalArgumentException();
    }
}

