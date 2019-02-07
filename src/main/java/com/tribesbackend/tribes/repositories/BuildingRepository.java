package com.tribesbackend.tribes.repositories;

import com.tribesbackend.tribes.models.Building;
import com.tribesbackend.tribes.models.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository <Building, Long> {


    List<Building> findAllByKingdom(Kingdom kingdom);

    List<Building> findByKingdomIdAndType(Long id, String type);
    }

