package com.tribes_backend.tribes.repository;


import com.tribes_backend.tribes.kingdomModel.Kingdom;
import org.springframework.data.repository.CrudRepository;

public interface KingdomRepository extends CrudRepository <Kingdom, Long> {
}
