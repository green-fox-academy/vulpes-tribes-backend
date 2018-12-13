package com.tribes_backend.tribes.tribesUser.repository;
import com.tribes_backend.tribes.tribesUser.model.TribesUser;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserTRepository extends JpaRepository<TribesUser, Long> {
    TribesUser findTribesUserByUsername (String username);

    @Override
    List<TribesUser> findAll();

    TribesUser findByUsername(String username);



}
