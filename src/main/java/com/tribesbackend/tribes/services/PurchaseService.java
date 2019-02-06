package com.tribesbackend.tribes.services;

import com.tribesbackend.tribes.models.ResourcesModel;
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
            return itemPriceRepository.findByType(type).get().getGold() * level/2;
        } else throw new IllegalArgumentException();
    }

    public boolean purchasableItem(Long kingdom_id, String type, int level) {
        if (resourceRepository.findByKingdom_IdAndType(kingdom_id, "gold").isPresent()) {
            return resourceRepository.findByKingdom_IdAndType(kingdom_id, "gold").get().getAmount() >= priceOfItem(type, level);
        } else throw new IllegalArgumentException();
    }

    public long currentGoldAmount(long kingdomId) {
        return resourceRepository.findByKingdom_IdAndType(kingdomId, "gold").get().getAmount();
    }

    public void decreaseGold(long desiredBuildingLevel, long kingdomId, String itemType) {

        if (resourceRepository.findByKingdom_IdAndType(kingdomId, "gold").get().getAmount() > 0) {
            ResourcesModel updated = resourceRepository.findByKingdom_IdAndType(kingdomId, "gold").get();
            updated.setAmount(currentGoldAmount(kingdomId) - priceOfItem(itemType, desiredBuildingLevel));
            resourceRepository.save(updated);
        } else throw new IllegalArgumentException();
    }
}
