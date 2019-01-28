package com.tribesbackend.tribes.repositories;

import com.tribesbackend.tribes.models.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KingdomRepository extends JpaRepository<Kingdom, Long> {

    @Override
    List<Kingdom> findAll();
    Optional<Kingdom> findKingdomByTribesUserUsername(String username);
}
