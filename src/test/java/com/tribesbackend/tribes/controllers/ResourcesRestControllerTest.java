package com.tribesbackend.tribes.controllers;

import com.tribesbackend.tribes.factories.KingdomFactory;
import com.tribesbackend.tribes.factories.ResourcesFactory;
import com.tribesbackend.tribes.factories.TribesUserFactory;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.ResourcesModel;
import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import com.tribesbackend.tribes.services.resourcesservice.ResourceService;
import com.tribesbackend.tribes.services.responseservice.ErrorMessagesMethods;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
public class ResourcesRestControllerTest {
    MockMvc mockMvc;
    @Mock
    ErrorMessagesMethods errorMessagesMethods;
    @Mock
    ResourceService resourceService;
    @Mock
    ResourceRepository resourceRepository;

    @InjectMocks
    ResourcesRestController resourcesRestController;

    ResourcesFactory resourcesFactory = new ResourcesFactory();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(resourcesRestController).build();
        TribesUser testUser = TribesUserFactory.createValidSampleTribesUser();

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(testUser.getUsername(), testUser.getPassword()));
    }

    @Test
    public void getResourcesTest() throws Exception {
        Kingdom kingdom = KingdomFactory.createValidSampleKingdom();
        List<ResourcesModel> rmList = resourcesFactory.createValidSampleResources();
        kingdom.setResourcesModel(rmList);
        Mockito.when(resourceService.resourceDisplayandUpdate("Vojtisek")).thenReturn(rmList);
//
//        mockMvc.perform(get("/kingdom/resources"))
//                .andExpect(status().isOk());
    }

}
