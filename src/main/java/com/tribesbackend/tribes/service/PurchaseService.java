package com.tribesbackend.tribes.service;


import com.tribesbackend.tribes.ItemPriceRepository;
import com.tribesbackend.tribes.tribesresources.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PurchaseService {

    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ItemPriceRepository itemPriceRepository;

    public int priceOfItem(int level, String type) {

        if (level >= 1 && level <= 5 && itemPriceRepository.findByType(type).isPresent()) {
            return itemPriceRepository.findByType(type).get().getGold() * level;
        } else throw new IllegalArgumentException();
    }

    public boolean purchasableItem(Long kingdom_id, String type, int level) {
        if (resourceRepository.findByKingdom_IdAndType(kingdom_id, "gold").isPresent()) {
            return resourceRepository.findByKingdom_IdAndType(kingdom_id, "gold").get().getAmount() >= resourceRepository.findByKingdom_IdAndType(kingdom_id, "gold").get().getAmount();
        } else throw new IllegalArgumentException();
    }
}
