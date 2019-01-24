package com.tribesbackend.tribes.services;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.ResourcesModel;
import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.services.resourcesservice.ResourceService;
import com.tribesbackend.tribes.repositories.ResourceRepository;
import com.tribesbackend.tribes.services.timeservice.TimeService;
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
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class ResourcesModelServiceTest {

    private TribesUser testUser = new TribesUser("Alex", "123");
    private Kingdom testKingdom = new Kingdom("mockDom", testUser);
    private ResourcesModel modelGold = new ResourcesModel("gold",100, testKingdom);
    private ResourcesModel modelFood = new ResourcesModel("food", 0, testKingdom);
    private List<ResourcesModel> listOfResources = Arrays.asList(modelGold, modelFood);




    @Mock
    private ResourceRepository resourceRepository;

    @Mock
    private KingdomRepository kingdomRepository;

    @Mock
    private TimeService timeService;

    @InjectMocks
    private ResourceService resourceService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCurrentTimestampTest() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp toTest = resourceService.getCurrentTimestamp();
        assertEquals(now, toTest);
    }

    @Test
    public void verifyResourceOptionalTest() {
        Optional<ResourcesModel> testOptional = Optional.of(modelGold);
        Optional<ResourcesModel> emptyOptional = Optional.ofNullable(null);
        assertEquals(testOptional.get(), modelGold);
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
    public void verifyTimestampHasValueZeroTimeTest(){
        ResourcesModel testModel = new ResourcesModel("gold", 0, testKingdom);
        testModel.setTimeStampLastVisit(0);
        long now = new Timestamp(System.currentTimeMillis()).getTime();
        long fromFnc = resourceService.verifyTimestampHasValue(testModel).getTime();
        assertEquals(now, fromFnc);
    }

    @Test
    public void verifyTimestampHasValueTest(){
        modelGold.setTimeStampLastVisit(1548322289246L);
        long directlyFromModel = modelGold.getTimeStampLastVisit();
        long fromFnc = resourceService.verifyTimestampHasValue(modelGold).getTime();
        assertEquals(directlyFromModel, fromFnc);
    }
//        return TimeService.timeDifferenceInMin(verifyTimestampHasValue(extractResourceFromKingdom(username)), getCurrentTimestamp());

    /*@Test
    public void getDifferenceInMinutesZeroDiff(){
        testKingdom.setResourcesModel(listOfResources);
        modelGold.setTimeStampLastVisit(1548322289246L);
        Timestamp stamp = new Timestamp(modelGold.getTimeStampLastVisit());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long differenceInMin = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - stamp.getTime());
        modelGold.setTimeStampLastVisit(stamp.getTime());
       // Mockito.when(resourceService.extractResourceFromKingdom("Alf")).thenReturn(modelGold);
       // Mockito.when(resourceService.verifyTimestampHasValue(modelGold)).thenReturn(stamp);

        assertEquals(differenceInMin, resourceService.getDifferenceInMinutes("Alf"));
    }*/

}

