package com.tribes_backend.tribes.repository;

import com.tribes_backend.tribes.userModel.TribesUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public interface UserTRepository extends JpaRepository<TribesUser, Long> {

    @Override
    List<TribesUser> findAll();



}
