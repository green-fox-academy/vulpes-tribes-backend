package com.tribesbackend.tribes.service;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.resourcesmodels.ResourcesModel;
import com.tribesbackend.tribes.repositories.UserTRepository;
import com.tribesbackend.tribes.services.resourcesservice.ResourceService;
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
import java.sql.Timestamp;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class ResourcesModelServiceTest {

    MockMvc mockMvc;

    @Mock
    private ResourceRepository resourceRepository;

    @InjectMocks
    private ResourceService resourceService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(resourceService).build();
    }

    @Test
    public void getCurrentTimestampTest() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp toTest = resourceService.getCurrentTimestamp();
        assertEquals(now, toTest);
    }


    @Test
    public void verifyResource() {
        Kingdom testKingdom = new Kingdom();
        ResourcesModel model = new ResourcesModel("gold", testKingdom);
        long id = 1;
        Optional<ResourcesModel> testOptional = Optional.of(model);
        Optional<ResourcesModel> emptyOptional = Optional.ofNullable(null);
        ResourcesModel resourcesModel = new ResourcesModel("gold", testKingdom);
        Mockito.when(resourceRepository.findResourceByResourcesId(id)).thenReturn(testOptional);

    }

    @Test
    public void mockTest(){
        UserTRepository r = mock(UserTRepository.class);

    }
}
