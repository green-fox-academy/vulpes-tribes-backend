package com.tribesbackend.tribes.troop.model;

import com.tribesbackend.tribes.factories.TroopFactory;
import com.tribesbackend.tribes.models.Troop;
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
    @After
    public void close() {
        validatorFactory.close();
    }

    @Test
    public void isValid() {
        Troop sampleTroop = TroopFactory.createSampleTroop();
        Troop invalidSampleTroop = TroopFactory.createInvalidSampleTroop();

        //when:
        Set<ConstraintViolation<Troop>> violations
                = validator.validate(sampleTroop);

        //then:
        assertTrue(violations.isEmpty());
    }

    @Test
    public void isNotValid() {
        Troop invalidSampleTroop = TroopFactory.createInvalidSampleTroop();
        //when:
        Set<ConstraintViolation<Troop>> violations
                = validator.validate(invalidSampleTroop);

        //then:
        assertEquals(violations.size(), 1);

        ConstraintViolation<Troop> violation
                = violations.iterator().next();
        assertEquals("The value must be positive",
                violation.getMessage());
        assertEquals("attack", violation.getPropertyPath().toString());
        assertEquals(-50, violation.getInvalidValue());
    }
}
