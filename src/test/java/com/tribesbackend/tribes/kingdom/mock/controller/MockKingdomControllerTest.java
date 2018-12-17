package com.tribesbackend.tribes.kingdom.mock.controller;

import com.tribesbackend.tribes.tribeskingdom.controllers.MockKingdomController;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
public class MockKingdomControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private MockKingdomController mockKingdomController;
}
