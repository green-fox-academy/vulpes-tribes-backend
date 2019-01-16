package com.tribesbackend.tribes.troop.repository;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.troop.model.Troop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TroopRepository extends JpaRepository<Troop, Long> {

    @Override
    List<Troop> findAll();

    Optional<Troop>findAllByKingdom(Kingdom kingdom);
}
