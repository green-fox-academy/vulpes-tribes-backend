package com.tribesbackend.tribes.services.resourcesservice;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.services.KingdomService;
import com.tribesbackend.tribes.services.timeservice.TimeService;
import com.tribesbackend.tribes.models.Resources.ResourcesModel;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ResourceService {
    ResourceRepository resourceRepository;
    KingdomRepository kingdomRepository;
    KingdomService kingdomService;
    TimeService timeService ;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository, KingdomRepository kingdomRepository,
                           KingdomService kingdomService ) {
        this.resourceRepository = resourceRepository;
        this.kingdomRepository = kingdomRepository;
        this.kingdomService = kingdomService;
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

    public List<ResourcesModel> getRMListFromDB(String username) {
        Kingdom kingdomFromDB = kingdomService.verifyKingdom(username);
        List<ResourcesModel> rm = kingdomFromDB.getResourcesModel();
        return rm;
    }

    public Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public long getCurrentTimeAsLong() {
        return getCurrentTimestamp().getTime();
    }

    public long timeDifferenceInMinIn(long timestamp1, long timestamp2) {
        long milliseconds = timestamp2 - timestamp1;
        return TimeUnit.MILLISECONDS.toMinutes(milliseconds);
    }

    public List<ResourcesModel> resourceDisplayandUpdate(String username, int amountGeneratedPerMinute) {
        Kingdom kingdomFromDB = kingdomService.verifyKingdom(username);
        List<ResourcesModel> rmListFromDB = kingdomFromDB.getResourcesModel();
        for (ResourcesModel r : rmListFromDB) {
            r.setAmount(r.getAmount() + (timeDifferenceInMinIn(r.getUpdatedAt(),System.currentTimeMillis()) * amountGeneratedPerMinute));
            r.setUpdatedAt(getCurrentTimestamp().getTime());
            resourceRepository.save(r);
        }

//        rmListFromDB.forEach(resourcesModel -> {
//            resourcesModel.setAmount(resourcesModel.getAmount() + (getDifferenceInMinutes(username) * amountGeneratedPerMinute));
//            resourcesModel.setUpdatedAt(getCurrTimeAsLong());
//            resourceRepository.save(resourcesModel);
//        });

//       for (int i = 0; i < rmListFromDB.size() ; i++) {
//            rmListFromDB.get(i).setAmount(rmListFromDB.get(i).getAmount() +(getDifferenceInMinutes(username) * amountGeneratedPerMinute));
//            rmListFromDB.get(i).setUpdatedAt(getCurrentTimestamp().getTime());
//            resourceRepository.save(rmListFromDB.get(i));
//        }
        //kingdomFromDB.setResourcesModel(rmListFromDB);
        //rmListFromDB.forEach(rm -> resourceRepository.save(rm));
        //kingdomRepository.save(kingdomFromDB);
        return rmListFromDB;
    }
}
