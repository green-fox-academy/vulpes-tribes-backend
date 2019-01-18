package com.tribesbackend.tribes.tribesbuilding.repository;

import com.tribesbackend.tribes.tribesbuilding.model.Building;
import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository <Building, Long> {
        Optional<Building> findByType(String type);
    }
