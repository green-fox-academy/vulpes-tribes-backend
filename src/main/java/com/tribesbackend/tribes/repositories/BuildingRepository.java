package com.tribesbackend.tribes.repositories;
import com.tribesbackend.tribes.models.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository <Building, Long> {

        List<Building> findByKingdomIdAndType(Long id, String type);
    }
