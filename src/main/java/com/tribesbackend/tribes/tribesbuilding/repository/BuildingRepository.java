package com.tribesbackend.tribes.tribesbuilding.repository;

import com.tribesbackend.tribes.tribesbuilding.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository <Building, Long> {
        Optional<Building> findByType(String type);
        Optional<Building> findById(int id);
    }
