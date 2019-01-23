package com.tribesbackend.tribes.services;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.ResourcesModel;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class ResourcesModelServiceTest {

    Kingdom testKingdom = new Kingdom();
    ResourcesModel model = new ResourcesModel("gold",100, testKingdom);
    MockMvc mockMvc;

    @Mock
    private ResourceRepository resourceRepository;

    @InjectMocks
    private ResourceService resourceService;

    @Before
    public void setUp(){
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
    public void verifyResourceOptionalTest() {
        Optional<ResourcesModel> testOptional = Optional.of(model);
        Optional<ResourcesModel> emptyOptional = Optional.ofNullable(null);
        assertEquals(testOptional.get(), model);
        assertFalse(emptyOptional.isPresent());
    }

    @Test
    public void verifyResourceReturnTest(){
        Kingdom testKingdom = new Kingdom();
        ResourcesModel model = new ResourcesModel("gold",100, testKingdom);
        long id = 1;
        Optional<ResourcesModel> testOptional = Optional.of(model);
        Mockito.when(resourceRepository.findResourceByResourcesId(id)).thenReturn(testOptional);
        assertEquals(resourceService.verifyResource(id), model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyResourceEmptyOptionalTest(){
        long id = 1;
        Optional<ResourcesModel> emptyOptional = Optional.ofNullable(null);
        Mockito.when(resourceRepository.findResourceByResourcesId(id)).thenReturn(emptyOptional);
        resourceService.verifyResource(id);
    }

    @Test
    public void verifyResourceSecondTest(){
        Kingdom testKingdom = new Kingdom();
        ResourcesModel model = new ResourcesModel("gold",100, testKingdom);
        long id = 1;
        Optional<ResourcesModel> testOptional = Optional.of(model);
        Mockito.when(resourceRepository.findResourceByResourcesId(id)).thenReturn(testOptional);
        ResourcesModel verified = resourceService.verifyResource(id);
        assertEquals("gold", verified.getType());
    }

    @Test
    public void getLastTimestampFromDBTest(){
        long id = 1;
        Kingdom testKingdom = new Kingdom();
        ResourcesModel model = new ResourcesModel("gold",100, testKingdom);
        model.setTimeStampLastVisit(100);
        assertEquals(resourceService.getLastTimestampFromDB(model), new Timestamp(100));
    }

    @Test
    public void verifyTimestampHasValueTest() {
        ResourcesModel testModel = new ResourcesModel();
    }
}

