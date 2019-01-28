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

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class KingdomServiceTest {
    private TribesUser testUser = new TribesUser("Alf", "123");
    private Kingdom testKingdom = new Kingdom("mockdom", testUser);
    private Optional<Kingdom> testOpt = Optional.of(testKingdom);

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
        Mockito.when(kingdomService.getOptKingdom("Alf")).thenReturn(testOpt);
        assertTrue(testOpt.isPresent());
        assertEquals("mockdom", testOpt.get().getName());
        assertEquals("Alf", testOpt.get().getTribesUser().getUsername());
    }

    @Test
    public void verifyKingdomNotNullTest(){
        Mockito.when(kingdomService.getOptKingdom("Alf")).thenReturn(testOpt);
        assertTrue(testOpt.isPresent());
        assertEquals(testKingdom, kingdomService.verifyKingdom("Alf"));
        assertEquals("Alf", kingdomService.verifyKingdom("Alf").getTribesUser().getUsername());
    }

    @Test(expected = NoSuchElementException.class)
    public void verifyKingdomNullValueTest(){
        Optional<Kingdom> nullOptKingdom = Optional.ofNullable(null);
        Mockito.when(kingdomService.getOptKingdom("Alf")).thenReturn(nullOptKingdom);
        Kingdom nullKingdom = kingdomService.verifyKingdom("Alf");
    }






}
