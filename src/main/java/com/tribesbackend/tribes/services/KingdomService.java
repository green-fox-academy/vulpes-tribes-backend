package com.tribesbackend.tribes.services;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.services.responseservice.ErrorMessagesMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class KingdomService {

    ErrorMessagesMethods errorMessagesMethods;
    KingdomRepository kingdomRepo;

    @Autowired
    public KingdomService(ErrorMessagesMethods errorMessagesMethods, KingdomRepository kingdomRepo) {
        this.errorMessagesMethods = errorMessagesMethods;
        this.kingdomRepo = kingdomRepo;
    }

    public Optional<Kingdom> getOptKingdom(String username){
        return kingdomRepo.findKingdomByTribesUserUsername(username);
    }

    public Kingdom verifyKingdom (String username){
        return getOptKingdom(username).orElseThrow(NoSuchElementException::new);
    }


}
