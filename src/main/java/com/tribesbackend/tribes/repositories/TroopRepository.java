
package com.tribesbackend.tribes.repositories;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.Troop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TroopRepository extends JpaRepository<Troop, Long> {

    @Override
    List<Troop> findAll();

    List<Troop>findAllByKingdom(Kingdom kingdom);
}
