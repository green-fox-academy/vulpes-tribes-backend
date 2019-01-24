package com.tribesbackend.tribes.services.resourcesservice;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.services.timeservice.TimeService;
import com.tribesbackend.tribes.models.ResourcesModel;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {
    ResourceRepository resourceRepository;
    KingdomRepository kingdomRepository;
    @Autowired
    public ResourceService(ResourceRepository resourceRepository, KingdomRepository kingdomRepository) {
        this.resourceRepository = resourceRepository;
        this.kingdomRepository = kingdomRepository;
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
        for(ResourcesModel rm: resourcesList){
            switch (rm.getType()){
                case "gold":
                    goldModel = rm;
                    return goldModel;
                case "food":
                    foodModel = rm;
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
