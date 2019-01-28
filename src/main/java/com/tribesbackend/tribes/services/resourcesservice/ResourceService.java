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

@Service
public class ResourceService {
    ResourceRepository resourceRepository;
    KingdomRepository kingdomRepository;
    KingdomService kingdomService;
    @Autowired
    public ResourceService(ResourceRepository resourceRepository, KingdomRepository kingdomRepository, KingdomService kingdomService) {
        this.resourceRepository = resourceRepository;
        this.kingdomRepository = kingdomRepository;
        this.kingdomService = kingdomService;
    }

    public List<ResourcesModel> newUserResourcesPreFill(Kingdom newKingdom){
        List<ResourcesModel> preFilled = new ArrayList<>();
        preFilled.add(new ResourcesModel("gold", 380, newKingdom));
        preFilled.add(new ResourcesModel("food", 0, newKingdom));
        return setDefaultTimestamps(preFilled);

    }
    public List<ResourcesModel> setDefaultTimestamps(List<ResourcesModel> resourcesModelsList){
        for(ResourcesModel r : resourcesModelsList){
            r.setUpdatedAt(getCurrentTimestamp().getTime());
        }
        return resourcesModelsList;
    }
    //lets refactor working with optional first - into new simple method

    public List<ResourcesModel> getRMListFromDB(String username){
        Kingdom kingdomFromDB = kingdomService.verifyKingdom(username);
        List<ResourcesModel> rm = kingdomFromDB.getResourcesModel();
        return rm;
    }


    public Timestamp getCurrentTimestamp(){
        return new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getLastTimestampFromDB (ResourcesModel verifiedResourcesModel) {
        return new Timestamp(verifiedResourcesModel.getUpdatedAt());
    }

    public Timestamp verifyTimestampHasValue (ResourcesModel verifiedResourcesModel){
        if (verifiedResourcesModel.getUpdatedAt() == 0){
            return getCurrentTimestamp();
        }
        else return getLastTimestampFromDB(verifiedResourcesModel);
    }

    public long getDifferenceInMinutes (String username){
        List<ResourcesModel> withTimeStamps = getRMListFromDB(username);
        return TimeService.timeDifferenceInMin(verifyTimestampHasValue(withTimeStamps.get(0)), getCurrentTimestamp());
    }

    public Kingdom resourceDisplayandUpdate (String username, int amountGeneratedPerMinute){
        Kingdom kingdomFromDB = kingdomService.verifyKingdom(username);
        List<ResourcesModel> rmListFromDB = kingdomFromDB.getResourcesModel();
        long updatedResourcesAmount;
        for(ResourcesModel r : rmListFromDB ){
            updatedResourcesAmount = r.getAmount() + (getDifferenceInMinutes(username) * amountGeneratedPerMinute);
            r.setAmount(updatedResourcesAmount);
            r.setUpdatedAt(getCurrentTimestamp().getTime());
        }
        kingdomFromDB.setResourcesModel(rmListFromDB);
        kingdomRepository.save(kingdomFromDB);
        return kingdomFromDB;
    }
}
