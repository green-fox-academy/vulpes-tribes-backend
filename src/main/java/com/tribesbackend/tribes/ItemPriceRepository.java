package com.tribesbackend.tribes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemPriceRepository extends JpaRepository <ItemPrice, Long> {
    Optional<ItemPrice> findByType(String type);
}
