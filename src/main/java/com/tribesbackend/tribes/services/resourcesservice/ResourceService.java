package com.tribesbackend.tribes.services.resourcesservice;

import com.tribesbackend.tribes.models.Building;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.BuildingRepository;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.services.KingdomService;
import com.tribesbackend.tribes.models.ResourcesModel;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ResourceService {
    ResourceRepository resourceRepository;
    KingdomRepository kingdomRepository;
    KingdomService kingdomService;
    BuildingRepository buildingRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository, KingdomRepository kingdomRepository,
                           KingdomService kingdomService, BuildingRepository buildingRepository) {
        this.resourceRepository = resourceRepository;
        this.kingdomRepository = kingdomRepository;
        this.kingdomService = kingdomService;
        this.buildingRepository = buildingRepository;
    }

    public List<ResourcesModel> newUserResourcesPreFill(Kingdom newKingdom) {
        List<ResourcesModel> preFilled = new ArrayList<>();
        preFilled.add(new ResourcesModel("gold", 380, newKingdom));
        preFilled.add(new ResourcesModel("food", 0, newKingdom));
        return setDefaultTimestamps(preFilled);
    }

    public List<ResourcesModel> setDefaultTimestamps(List<ResourcesModel> resourcesModelsList) {
        for (ResourcesModel r : resourcesModelsList) {
            r.setUpdatedAt(getCurrentTimestamp().getTime());
        }
        return resourcesModelsList;
    }

    public Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public long timeDifferenceInMinIn(long timestamp1, long timestamp2) {
        long milliseconds = timestamp2 - timestamp1;
        return TimeUnit.MILLISECONDS.toMinutes(milliseconds);
    }

    public List<Building> getGoldResources(Kingdom kingdom) {
        return buildingRepository.findAllByKingdom(kingdom).stream()
                .filter(building -> building.getType().equals("mine"))
                .collect(Collectors.toList());
    }

    public List<ResourcesModel> resourceDisplayandUpdate(String username, int amountGeneratedPerMinute) {
        Kingdom kingdomFromDB = kingdomService.verifyKingdom(username);
        List<ResourcesModel> rmListFromDB = kingdomFromDB.getResourcesModel();
        for (ResourcesModel r : rmListFromDB) {
            long OriginalUpdatedAt = r.getUpdatedAt();
            r.setAmount(r.getAmount() + (timeDifferenceInMinIn(OriginalUpdatedAt, System.currentTimeMillis()) * amountGeneratedPerMinute));
            r.setUpdatedAt(getCurrentTimestamp().getTime());
            r.setGenerated(timeDifferenceInMinIn(OriginalUpdatedAt, System.currentTimeMillis()));
            resourceRepository.save(r);
        }
        return rmListFromDB;
    }
}
