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

    Optional<TribesUser> findByUsername(String username);

    //Added by Jirina coz of login endpoint functionality - .getPassword by a TribesUser from database
    TribesUser findTribesUserByUsername(String username);
}
