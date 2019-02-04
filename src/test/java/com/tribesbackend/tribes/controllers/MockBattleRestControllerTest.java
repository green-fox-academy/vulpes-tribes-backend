package com.tribesbackend.tribes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tribesbackend.tribes.factories.KingdomFactory;
import com.tribesbackend.tribes.factories.TribesUserFactory;
import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.models.jsonmodels.RegistrationResponseJson;
import com.tribesbackend.tribes.models.jsonmodels.TroopIdsJson;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.repositories.UserTRepository;
import com.tribesbackend.tribes.services.BattleService;
import com.tribesbackend.tribes.services.ObjectToJsonConverter;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MockBattleRestControllerTest {
    @Mock
    UserTRepository userTRepository;
    @Mock
    KingdomRepository kingdomRepository;
    @Mock
    BattleService battleService;
    @InjectMocks
    MockBattleRestController mockBattleRestController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(mockBattleRestController).build();
        TribesUser testUser = TribesUserFactory.createValidSampleTribesUser();
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(testUser.getUsername(), testUser.getPassword()));
    }

    @Test
    public void getPlayerListTest() throws Exception {
        RegistrationResponseJson registrationResponseJson = new RegistrationResponseJson((long) 1, "Vojtisek", (long) 1, "No avatar yet", 0);
        List<RegistrationResponseJson> registrationResponseJsonList = new ArrayList<>();
        registrationResponseJsonList.add(registrationResponseJson);
        mockBattleRestController.setUserTRepository(userTRepository);
        when(userTRepository.findTribesUserByUsername("Vojtisek")).thenReturn(Optional.of(TribesUserFactory.createValidSampleTribesUser()));
        when(battleService.otherPlayers("Vojtisek")).thenReturn(registrationResponseJsonList);
        mockMvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void startBattleTest() throws Exception {
        List<Long> listOfIds = Arrays.asList((long) 1, (long) 2, (long) 3);
        TroopIdsJson troopIdsJson = new TroopIdsJson(listOfIds);
        when(kingdomRepository.findKingdomById((long) 1)).thenReturn(KingdomFactory.createOptionalValidSampleKingdom());
        mockMvc.perform(post("/kingdom/{id}/battle", "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(ObjectToJsonConverter.asJsonString(troopIdsJson)))
                .andExpect(status().isOk());
    }
    @Test
    public void battleNotStartedTest() throws Exception {
        List<Long> listOfIds = Arrays.asList((long) 1, (long) 2, (long) 3);
        TroopIdsJson troopIdsJson = new TroopIdsJson(listOfIds);
        when(kingdomRepository.findKingdomById((long) 1)).thenReturn(Optional.empty());
        mockMvc.perform(post("/kingdom/{id}/battle", "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(ObjectToJsonConverter.asJsonString(troopIdsJson)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status", Matchers.is("error")))
                .andExpect(jsonPath("$.message", Matchers.is("No such kingdom")));
    }
}