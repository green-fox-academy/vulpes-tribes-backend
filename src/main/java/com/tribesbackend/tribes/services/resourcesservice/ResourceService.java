package com.tribesbackend.tribes.services.resourcesservice;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.services.KingdomService;
import com.tribesbackend.tribes.services.timeservice.TimeService;
import com.tribesbackend.tribes.models.ResourcesModel;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            r.setTimeStampLastVisit(getCurrentTimestamp().getTime());
        }
        return resourcesModelsList;
    }

    public ResourcesModel extractResourceFromKingdom (String username){
        Optional<Kingdom> optionalKingdom = kingdomRepository.findKingdomByTribesUserUsername(username);
        Kingdom kingdom = new Kingdom();
        if(optionalKingdom.isPresent()){
             kingdom = optionalKingdom.get();
        }
        ResourcesModel goldModel;
        ResourcesModel foodModel = new ResourcesModel();
        List<ResourcesModel> resourcesList = kingdom.getResourcesModel();
        for(ResourcesModel r: resourcesList){
            switch (r.getType()){
                case "gold":
                    goldModel = r;
                    return goldModel;
                case "food":
                    foodModel = r;
                    break;
            }
        }
        return foodModel;
    }

    public ResourcesModel verifyResource (long id) {
        Optional<ResourcesModel> optionalResource = resourceRepository.findResourceByResourcesId(id);
        if (optionalResource.isPresent()) {
            return optionalResource.get();
        } else throw new IllegalArgumentException();

        //return Optional.ofNullable(optionalResource)
        //      .map(r -> r.get())
        //      .orElseThrow(IllegalArgumentException::new);
    }

    public Timestamp getCurrentTimestamp(){
        return new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getLastTimestampFromDB (ResourcesModel verifiedResourcesModel) {
        return new Timestamp(verifiedResourcesModel.getTimeStampLastVisit());
    }

    public Timestamp verifyTimestampHasValue (ResourcesModel verifiedResourcesModel){
        if (verifiedResourcesModel.getTimeStampLastVisit() == 0){
            return getCurrentTimestamp();
        }
        else return getLastTimestampFromDB(verifiedResourcesModel);
    }

    public long getDifferenceInMinutes (String username){
        return TimeService.timeDifferenceInMin(verifyTimestampHasValue(extractResourceFromKingdom(username)), getCurrentTimestamp());
    }

    public ResourcesModel resourceDisplayandUpdate (String username, int amountGeneratedPerMinute){
        ResourcesModel resourcesModel = extractResourceFromKingdom(username);
        long updatedResourceAmount = resourcesModel.getAmount() + (getDifferenceInMinutes(username) * amountGeneratedPerMinute);
        resourcesModel.setAmount(updatedResourceAmount);
        resourcesModel.setTimeStampLastVisit(getCurrentTimestamp().getTime());
        resourceRepository.save(resourcesModel);
        return resourcesModel;
    }
}
