package com.tribesbackend.tribes.troop.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
public class TroopTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @Before
    public void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    @Test
    public void isValid() {
        Troop sampleTroop = Troop.createSampleTroop();
        Troop invalidSampleTroop = Troop.createIvalidSampleTroop();

        //when:
        Set<ConstraintViolation<Troop>> violations
                = validator.validate(sampleTroop);

        //then:
        assertTrue(violations.isEmpty());
    }

    @Test
    public void isNotValid() {
        Troop invalidSampleTroop = Troop.createIvalidSampleTroop();
        //when:
        Set<ConstraintViolation<Troop>> violations
                = validator.validate(invalidSampleTroop);

        //then:
        assertEquals(violations.size(), 1);

        ConstraintViolation<Troop> violation
                = violations.iterator().next();
        assertEquals("size must be between 3 and 3",
                violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
        assertEquals("a", violation.getInvalidValue());
    }

    @After
    public void close() {
        validatorFactory.close();
    }
}