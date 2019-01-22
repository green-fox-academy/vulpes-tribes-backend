package com.tribesbackend.tribes;

import com.tribesbackend.tribes.models.buildingmodels.Building;
import com.tribesbackend.tribes.factories.BuildingFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)

public class BuildingTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @Before
    public void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @After
    public void close() {
        validatorFactory.close();
    }

    @Test
    public void buildingValid() {
        Building validSampleBuilding = BuildingFactory.createSampleBuilding();
        Set<ConstraintViolation<Building>> violations = validator.validate(validSampleBuilding);
        assertTrue(violations.isEmpty());
    }
}

