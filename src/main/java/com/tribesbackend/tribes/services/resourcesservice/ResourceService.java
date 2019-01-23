package com.tribesbackend.tribes.services.resourcesservice;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.services.timeservice.TimeService;
import com.tribesbackend.tribes.models.resourcesmodels.ResourcesModel;
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
    public ResourcesModel extractFromKingdom (String username){
        //or getActualKingdom() from token
        Optional<Kingdom> optionalKingdom = kingdomRepository.findKingdomByTribesUserUsername(username);
        Kingdom k = new Kingdom();
        if(optionalKingdom.isPresent()){
            k = optionalKingdom.get();
        }
        ResourcesModel goldModel = new ResourcesModel();
        ResourcesModel foodModel = new ResourcesModel();
        List<ResourcesModel> r = k.getResourcesModel();
        for(ResourcesModel rm: r){
            if(rm.getType().equals("gold")){
                goldModel = rm;
                return goldModel;
            }
            else foodModel = rm;
        }
        return foodModel;
    }

    //find by kingdom
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
            Timestamp ac = getCurrentTimestamp();
            return ac;
        }
        else return getLastTimestampFromDB(verifiedResourcesModel);
    }

    public long getDifferenceInMinutes (long id){
        return TimeService.timeDifferenceInMin(verifyTimestampHasValue(verifyResource(id)), getCurrentTimestamp());
    }

    public ResourcesModel resourceDisplayandUpdate (long id, int amountGeneratedPerMinute){
        ResourcesModel resourcesModel = verifyResource(id);
        long updatedResourceAmount = resourcesModel.getAmount() + (getDifferenceInMinutes(id) * amountGeneratedPerMinute);
        resourcesModel.setAmount(updatedResourceAmount);
        resourcesModel.setTimeStampLastVisit(getCurrentTimestamp().getTime());
        resourceRepository.save(resourcesModel);
        return resourcesModel;
    }
}
