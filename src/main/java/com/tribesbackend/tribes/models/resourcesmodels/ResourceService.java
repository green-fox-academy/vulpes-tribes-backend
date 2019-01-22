
import com.tribesbackend.tribes.models.resourcesmodels.ResourcesModel;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import com.tribesbackend.tribes.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class ResourceService {
    ResourceRepository resourceRepository;
    @Autowired
    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public ResourcesModel verifyResource (long id) {
        Optional<ResourcesModel> optionalResource = resourceRepository.findResourceByResourcesId(id);
        if (optionalResource.isPresent()) {
            return optionalResource.get();
        } else throw new IllegalArgumentException();
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
