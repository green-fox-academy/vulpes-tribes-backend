package com.tribesbackend.tribes;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


@RunWith(SpringJUnit4ClassRunner.class)
public class TribesResourcesModelTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @Before
    public void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void resourceIsValid() {

    }
}
