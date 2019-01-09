package com.tribesbackend.tribes.tribesresource.repository;

import com.tribesbackend.tribes.tribesresource.model.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resources, Long> {
    List<Resources> findAll();

}
