package com.tribesbackend.tribes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BuildingTimeRepository extends JpaRepository<BuildingTime, Long> {
    Optional<BuildingTime> findByType(String type);
}
