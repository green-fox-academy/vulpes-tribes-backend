package com.tribesbackend.tribes.tribesresources.repository;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribesresources.model.ResourcesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<ResourcesModel, Long> {
    @Override
    List<ResourcesModel> findAll();

    Optional<ResourcesModel> findResourceByResourcesId(long id);

    Optional<ResourcesModel> findByKingdom_IdAndType(Long kingdom_id, String type);


}
