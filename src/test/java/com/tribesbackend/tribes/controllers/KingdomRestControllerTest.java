package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.factories.KingdomFactory;
import com.tribesbackend.tribes.models.jsonmodels.KingdomInputJson;
import com.tribesbackend.tribes.models.jsonmodels.KingdomResponseJson;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import org.hamcrest.Matchers;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

    @Mock
    private KingdomInputJson kingdomInputJson;

    @Mock
    private KingdomResponseJson kingdomResponseJson;


    @Test
    public void testGetKingdom() throws Exception {
        when(kingdomRepository.findKingdomByTribesUserUsername(any())).thenReturn(java.util.Optional.of(KingdomFactory.createValidSampleKingdom()));
        kingdomRestController.setKingdomRepository(kingdomRepository);
        mockMvc.perform(get("/kingdom"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testPutKingdom() throws Exception {
        String json = "{\n" +
                "  \"name\": \"azeho\",\n" +
                "\"locationX\":4,\n" +
                "\"locationY\":6\n" +
                "}";
        when(kingdomRepository.findKingdomByTribesUserUsername(any())).thenReturn(java.util.Optional
                .of(KingdomFactory.createValidSampleKingdom()));
        kingdomRestController.setKingdomRepository(kingdomRepository);
        mockMvc.perform(put("/kingdom")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("azeho")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.x", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.y", Matchers.is(6)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testIDnotfound() throws Exception {
        when(kingdomRepository.findKingdomById(any())).thenReturn(Optional.empty());
      kingdomRestController.setKingdomRepository(kingdomRepository);
        mockMvc.perform(get("/kingdom/{id}",2,3)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                  //    .content(Integer.toString (id)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Id not found")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("error")))
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    public void testIDOK() throws Exception {
        when(kingdomRepository.findKingdomById(any())).thenReturn(java.util.Optional.of(KingdomFactory.createValidSampleKingdom()));
        kingdomRestController.setKingdomRepository(kingdomRepository);
        mockMvc.perform(get("/kingdom/{id}", 1))
                //  .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("mightykingdom")))
                .andExpect(status().isOk())
                .andDo(print());
    }


}
