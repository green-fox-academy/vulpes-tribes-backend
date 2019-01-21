package com.tribesbackend.tribes.repositories;

import com.tribesbackend.tribes.models.buildingmodels.Building;
import com.tribesbackend.tribes.models.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    @Override
    List<Building> findAll();

    Optional<List<Building>> findAllByKingdom(Kingdom kingdom);
    Optional<Building> findByType(String type);
    Optional<Building> findById(int id);
}
