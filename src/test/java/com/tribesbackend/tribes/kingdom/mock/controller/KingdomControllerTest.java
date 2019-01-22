package com.tribesbackend.tribes.kingdom.mock.controller;

import com.tribesbackend.tribes.controllers.kingdomcontrollers.KingdomRestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class KingdomControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private KingdomRestController mockKingdomController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mockKingdomController).build();
    }

    @Test
    public void testMockJson() throws Exception {
        String json = "{\"name\":\"mockdom\",\"tribesUser\":{\"username\":\"mockUser\",\"password\":\"strongOne\"}}";

        mockMvc.perform(MockMvcRequestBuilders.get("/mock/kingdom"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }
}
