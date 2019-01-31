package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.factories.KingdomFactory;
import com.tribesbackend.tribes.factories.TribesUserFactory;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.repositories.UserTRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
public class MockBattleRestControllerTest {
    @Mock
    UserTRepository userTRepository;
    @Mock
    KingdomRepository kingdomRepository;
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
        List<TribesUser> users = new ArrayList<>();
        users.add(TribesUserFactory.createValidSampleTribesUser());
        users.add(TribesUserFactory.createValidSampleTribesUser());
        Mockito.when(userTRepository.findAll()).thenReturn(users);
        mockMvc.perform(MockMvcRequestBuilders.get("/players"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}