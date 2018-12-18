package com.tribesbackend.tribes.troop.repository;

import com.tribesbackend.tribes.troop.model.Troop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TroopRepository extends JpaRepository<Troop, Long> {
    List<Troop> findAll();
}
