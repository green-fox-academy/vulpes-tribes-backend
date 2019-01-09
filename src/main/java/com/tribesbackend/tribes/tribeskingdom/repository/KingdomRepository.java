package com.tribesbackend.tribes.tribeskingdom.repository;
import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KingdomRepository extends JpaRepository<Kingdom, Long> {
}
