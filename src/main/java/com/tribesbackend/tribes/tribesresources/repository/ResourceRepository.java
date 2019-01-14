package com.tribesbackend.tribes.tribesresources.repository;

import com.tribesbackend.tribes.tribesresources.model.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resources, Long> {
    List<Resources> findAll();

}
