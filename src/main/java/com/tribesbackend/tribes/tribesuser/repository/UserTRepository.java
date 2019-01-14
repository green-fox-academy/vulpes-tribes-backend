package com.tribesbackend.tribes.tribesuser.repository;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTRepository extends JpaRepository<TribesUser, Long> {
    @Override
    List<TribesUser> findAll();

    Optional<TribesUser> findTribesUserByUsername(String username);
}
