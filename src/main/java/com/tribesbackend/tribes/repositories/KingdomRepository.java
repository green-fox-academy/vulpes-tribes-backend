package com.tribesbackend.tribes.repositories;
import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KingdomRepository extends JpaRepository<Kingdom, Long> {

    @Override
    List<Kingdom> findAll();

    Kingdom findKingdomByTribesUserUsername(String username);
}
