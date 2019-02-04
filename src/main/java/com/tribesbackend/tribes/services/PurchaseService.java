package com.tribesbackend.tribes.services;

import com.tribesbackend.tribes.repositories.ItemPriceRepository;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ItemPriceRepository itemPriceRepository;


    public long priceOfItem(String type, long level) {
        if (level >= 1 && level <= 5 && itemPriceRepository.findByType(type).isPresent()) {
            return itemPriceRepository.findByType(type).get().getGold() * level;
        } else throw new IllegalArgumentException();
    }

    public boolean purchasableItem(Long kingdom_id, String type, int level) {
        if (resourceRepository.findByKingdom_IdAndType(kingdom_id, "gold").isPresent()) {
            return resourceRepository.findByKingdom_IdAndType(kingdom_id, "gold").get().getAmount() >= priceOfItem(type, level);
        } else throw new IllegalArgumentException();
    }

    public long currentGoldAmount (long kingdomId){
        return  resourceRepository.findByKingdom_IdAndType(kingdomId,"gold").get().getAmount();
    }

    public void decreaseGold(long desiredBuildingLevel, long kingdomId, String buildingType) {

        if (resourceRepository.findByKingdom_IdAndType(kingdomId,"gold").get().getAmount()>0) {
             resourceRepository.findByKingdom_IdAndType(kingdomId,"gold").get().setAmount(
                     currentGoldAmount(kingdomId)  - priceOfItem(buildingType,desiredBuildingLevel));
        } else throw new IllegalArgumentException();
    }
}

