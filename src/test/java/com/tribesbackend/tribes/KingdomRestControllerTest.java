package com.tribesbackend.tribes;

import com.tribesbackend.tribes.controllers.KingdomRestController;
import com.tribesbackend.tribes.factories.KingdomFactory;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class KingdomRestControllerTest {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    private MockMvc mockMvc;
    @InjectMocks
    private KingdomRestController kingdomRestController;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(kingdomRestController).build();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("player", "heslo"));
    }

    @Mock
    private KingdomRepository kingdomRepository;

    @Test
    public void testGetKingdom() throws Exception {
        when(kingdomRepository.findKingdomByTribesUserUsername(any())).thenReturn(java.util.Optional.of(KingdomFactory.createValidSampleKingdom()));
        kingdomRestController.setKingdomRepository(kingdomRepository);
        mockMvc.perform(get("/kingdom"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
