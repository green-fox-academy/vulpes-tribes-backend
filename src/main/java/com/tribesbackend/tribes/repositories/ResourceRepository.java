package com.tribesbackend.tribes.repositories;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.ResourcesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository <ResourcesModel, Long> {
    @Override
    List<ResourcesModel> findAll();

    Optional<ResourcesModel> findResourceByResourcesId(long id);
    Optional<ResourcesModel> findAllByKingdom (Kingdom kingdom);
    Optional<ResourcesModel> findByKingdom_IdAndType(Long kingdom_id, String type);

}
