package com.tribesbackend.tribes.repositories;
import com.tribesbackend.tribes.models.Building;
import com.tribesbackend.tribes.models.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository <Building, Long> {
        Optional<Building> findByType(String type);

        List<Building> findAllByKingdom(Kingdom kingdom);
    }
