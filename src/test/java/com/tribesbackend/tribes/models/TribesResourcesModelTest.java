package com.tribesbackend.tribes.models;


import com.tribesbackend.tribes.models.resources.ResourcesModel;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static org.junit.Assert.*;

public class TribesResourcesModelTest {

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
        ResourcesModel resourcesModel = new ResourcesModel.ResourcesBuilder()
                .setKingdom(new Kingdom())
                .setType("gold")
                .build();

        //when:
        Set<ConstraintViolation<ResourcesModel>> violations
                = validator.validate(resourcesModel);

        //then:
        assertTrue(violations.isEmpty());
    }
    
}
