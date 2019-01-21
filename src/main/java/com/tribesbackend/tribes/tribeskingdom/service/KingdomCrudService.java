package com.tribesbackend.tribes.tribeskingdom.service;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomCrudService {
    KingdomRepository kingdomRepository;

    @Autowired
    public KingdomCrudService (KingdomRepository kingdomRepository) {this.kingdomRepository = kingdomRepository; }

    public  void save(Kingdom newKingdom) throws Exception{
        kingdomRepository.save(newKingdom);
    }
}
