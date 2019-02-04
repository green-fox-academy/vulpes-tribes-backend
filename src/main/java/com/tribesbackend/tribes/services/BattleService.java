package com.tribesbackend.tribes.services;

import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.models.jsonmodels.RegistrationResponseJson;
import com.tribesbackend.tribes.repositories.UserTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BattleService {
    private UserTRepository userTRepository;
    @Autowired
    public BattleService(UserTRepository userTRepository) {
        this.userTRepository = userTRepository;
    }

    public List<RegistrationResponseJson> otherPlayers(String currentUsername) {
        List<TribesUser> users = userTRepository.findAll();
        List<RegistrationResponseJson> players = new ArrayList<>();
        for (TribesUser user:
                users) {
            if (!user.getUsername().equals(currentUsername)) {
                players.add(new RegistrationResponseJson(user.getId(), user.getUsername(), user.getKingdom().getId(), "No avatar yet", 0));
            }
        }
        return players;
    }
}
