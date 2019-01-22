package com.tribesbackend.tribes.kingdom.mock.controller;

import com.tribesbackend.tribes.controllers.kingdomcontrollers.KingdomRestController;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class KingdomRestControllerTest {

    @Mock
    private KingdomRepository kingdomRepository;

    @InjectMocks
    private KingdomRestController kingdomRestController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(kingdomRestController).build();
    }

//    @Test
//    public void testKingdomnameEqualsNull() throws Exception {
//        Kingdom newKingdom = new Kingdom(null);
//        mockMvc.perform(MockMvcRequestBuilders.)
//
//    }
}


