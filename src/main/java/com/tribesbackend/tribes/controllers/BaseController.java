package com.tribesbackend.tribes.controllers;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.repositories.UserTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import java.util.NoSuchElementException;


@Controller
public class BaseController {
    KingdomRepository kingdomRepository;
    UserTRepository userTRepository;
    @Autowired
    public void setUserTRepository(UserTRepository userTRepository) {
        this.userTRepository = userTRepository;
    }

    @Autowired
    public void setKingdomRepository(KingdomRepository kingdomRepository) {
        this.kingdomRepository = kingdomRepository;
    }

    public Kingdom getCurrentKingdom() {
        return kingdomRepository.findKingdomByTribesUserUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(NoSuchElementException::new);
    }

    public TribesUser getCurrentUser() {
        return userTRepository.findTribesUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    }
}
