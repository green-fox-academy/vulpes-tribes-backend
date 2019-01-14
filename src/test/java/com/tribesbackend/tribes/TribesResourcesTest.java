/*package com.tribesbackend.tribes;

import com.tribesbackend.tribes.tribesresources.model.Resources;
import com.tribesbackend.tribes.tribesresources.model.ResourcesFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;

public class TribesResourcesTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void shouldHaveNoViolations() {
        //given:
        Resources resources = new ResourcesFactory().createValidSampleResources();

        //when:
        Set<ConstraintViolation<Resources>> violations
                = validator.validate(resources);

        //then:
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidAmountSampleResources() {
        //given :
        Resources invalidAmountSampleResources = new ResourcesFactory().createInvalidAmountSampleResources();

        //when:
        Set<ConstraintViolation<Resources>> violations
                = validator.validate(invalidAmountSampleResources);

        //then:
        assertEquals(violations.size(), 1);

        ConstraintViolation<Resources> violation
                = violations.iterator().next();
        assertEquals("The value must be positive",
                violation.getMessage());
        assertEquals("amount", violation.getPropertyPath().toString());
        assertEquals(-5, violation.getInvalidValue());
    }

    @Test
    public void shouldDetectInvalidUpdated_atSampleSerousce() {
        //given :
        Resources invalidUpdated_atSampleResources = new ResourcesFactory().createInvalidUpdated_atSampleResources();

        //when:
        Set<ConstraintViolation<Resources>> violations
                = validator.validate(invalidUpdated_atSampleResources);

        //then:
        assertEquals(violations.size(), 1);

        ConstraintViolation<Resources> violation
                = violations.iterator().next();
        assertEquals("The value must be positive",
                violation.getMessage());
        assertEquals("updated_at", violation.getPropertyPath().toString());
        assertEquals(-987654321 , violation.getInvalidValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldDetectInvalidTypeSampleSerousce() {
        //given :
        Resources invalidTypeSampleResources = new ResourcesFactory().createInvalidTypeSampleResources();

        //when:
        Set<ConstraintViolation<Resources>> violations
                = validator.validate(invalidTypeSampleResources);

        //then:
        assertEquals(violations.size(), 1);

        ConstraintViolation<Resources> violation
                = violations.iterator().next();
        assertEquals("The value must be positive",
                violation.getMessage());
        assertEquals("type", violation.getPropertyPath().toString());
        assertEquals("silver" , violation.getInvalidValue());
    }
}*/
