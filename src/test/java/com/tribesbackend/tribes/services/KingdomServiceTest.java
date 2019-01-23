package com.tribesbackend.tribes.services;

import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class KingdomServiceTest {
    TribesUser testUser = new TribesUser("Alf", "123");
    Kingdom testKingdom = new Kingdom("mockdom", testUser);

    MockMvc mockMvc;

    @Mock
    private ResourceRepository resourceRepository;

    @Mock
    private KingdomRepository kingdomRepository;

    @InjectMocks
    private KingdomService kingdomService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(kingdomService).build();
    }

    @Test
    public void getOptKingdomTest(){
        Optional<Kingdom> testOpt = Optional.of(testKingdom);
        Mockito.when(kingdomService.getOptKingdom("Alf")).thenReturn(testOpt);
        assertEquals("mockdom", testOpt.get().getName());
        assertEquals("Alf", testOpt.get().getTribesUser().getUsername());
    }



}
