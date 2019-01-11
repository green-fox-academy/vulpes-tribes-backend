package com.tribesbackend.tribes.tribeskingdom.model;

import com.tribesbackend.tribes.tribeskingdom.repository.KingdomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KingdomModelHelpersMethods {
    KingdomRepository kingdomRepo;

    @Autowired
    public  KingdomModelHelpersMethods (KingdomRepository kingdomRepo) {
        this.kingdomRepo = kingdomRepo;
    }

    public static boolean isValid(Kingdom kingdom) {
        if (kingdom.getName()==null||kingdom.getName().equals("")){
            return false;}
            else return true;
    }

    public static boolean isEmpty(Kingdom kingdom) {
        return kingdom.getName().isEmpty() || kingdom.getName().equals(" ");
    }
}
