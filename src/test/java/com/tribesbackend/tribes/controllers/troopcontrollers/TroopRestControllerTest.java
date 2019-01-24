package com.tribesbackend.tribes.controllers.troopcontrollers;

import com.tribesbackend.tribes.controllers.TroopRestController;
import com.tribesbackend.tribes.factories.KingdomFactory;
import com.tribesbackend.tribes.factories.TokenFactory;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
        mockMvc = MockMvcBuilders.standaloneSetup(troopRestController)
                    .build();
    }

    @Test
    public void listTroopsTest() throws Exception{
        Optional<Kingdom> kingdom = KingdomFactory.createOptionalValidSampleKingdom();
        Mockito.when(kingdomRepository.findKingdomByTribesUserUsername("Vojtisek")).thenReturn(kingdom);
        mockMvc.perform(MockMvcRequestBuilders.get("/kingdom/troops")
                .header("X-Tribes-Token",
//                        ""))
                        TokenFactory.createValidToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
    }
}