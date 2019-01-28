package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.controllers.TroopRestController;
import com.tribesbackend.tribes.factories.KingdomFactory;
import com.tribesbackend.tribes.factories.TokenFactory;
import com.tribesbackend.tribes.factories.TribesUserFactory;
import com.tribesbackend.tribes.factories.TroopFactory;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.models.Troop;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@RunWith(SpringJUnit4ClassRunner.class)
public class TroopRestControllerTest {

    private MockMvc mockMvc;
    @Mock
    KingdomRepository kingdomRepository;
    @InjectMocks
    TroopRestController troopRestController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(troopRestController).build();
        TribesUser testUser = TribesUserFactory.createValidSampleTribesUser();
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(testUser.getUsername(), testUser.getPassword()));
    }

    @Test
    public void listTroopsTest() throws Exception{
        Optional<Kingdom> kingdom = KingdomFactory.createOptionalValidSampleKingdom();
        Troop troop = TroopFactory.createSampleTroop();
        kingdom.get().getTroops().add(troop);
        Mockito.when(kingdomRepository.findKingdomByTribesUserUsername("Vojtisek")).thenReturn(kingdom);
        mockMvc.perform(MockMvcRequestBuilders.get("/kingdom/troops"))
                .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
    }
}