package com.tribesbackend.tribes;

import com.tribesbackend.tribes.tribesbuilding.model.Building;
import com.tribesbackend.tribes.tribesbuilding.model.BuildingFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)

public class BuildingTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public void createValidator(){

        validatorFactory= Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public void close(){validatorFactory.close();}

    @Test
    public void buildingValid(){
        Building validSampleBuilding = BuildingFactory.createSampleBuilding();
      //  List<Building> invalidSampleBUildingList = BuildingFactory.createInvalidBuildingList();
        Set<ConstraintViolation<Building>> violations = validator.validate(validSampleBuilding);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void isNotValid(){
        List<Building> invalidBuildingList = BuildingFactory.createInvalidBuildingList();
        for (int i = 0; i <invalidBuildingList.size() ; i++) {
            Set<ConstraintViolation<Building>>violations = validator.validate(invalidBuildingList.get(i));
            assertEquals(violations.size(),1);

        }

    }
}
