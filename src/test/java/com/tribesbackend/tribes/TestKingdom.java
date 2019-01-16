package com.tribesbackend.tribes;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribeskingdom.model.KingdomModelHelpersMethods;
import com.tribesbackend.tribes.tribeskingdom.repository.KingdomRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestKingdom {
    @InjectMocks
    private KingdomModelHelpersMethods kingdomModelHelpersMethods;
    @Mock
    private KingdomRepository kingdomRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(kingdomModelHelpersMethods).build();
        kingdomModelHelpersMethods = new KingdomModelHelpersMethods(kingdomRepository);
    }

    @Test
    public void kingdomNameIsValid() {
        Kingdom kingdom = new Kingdom("kingdom");
        assertEquals(true, kingdomModelHelpersMethods.isValid(kingdom));
    }

    @Test
    public void kingdomNameIsNotValid() {
        Kingdom kingdom = new Kingdom(null);
        assertEquals(false, kingdomModelHelpersMethods.isValid(kingdom));
    }

    @Test
    public void kingdomNameIsEmpty() {
        Kingdom kingdom = new Kingdom("");
        assertEquals(false, kingdomModelHelpersMethods.isValid(kingdom));
    }

    @Test
    public void kingdomNameIsFilled() {
        Kingdom kingdom = new Kingdom("kingdomNameExample");
        assertEquals(true, kingdomModelHelpersMethods.isValid(kingdom));
    }
}
