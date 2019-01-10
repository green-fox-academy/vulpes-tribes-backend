package com.tribesbackend.tribes.tribesresource.repository;

import com.tribesbackend.tribes.tribesresource.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository <Resource, Long> {
    @Override
    List<Resource> findAll();


}
