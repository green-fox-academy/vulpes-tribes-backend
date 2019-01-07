package com.tribesbackend.tribes;

import com.tribesbackend.tribes.tribesresource.model.ResourcesFactory;
import com.tribesbackend.tribes.tribesresource.model.Resources;
import com.tribesbackend.tribes.tribesresource.model.ResourcesModelHelpersMethods;
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

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;



@RunWith(SpringJUnit4ClassRunner.class)
public class TestResources {

    @Test
    public void resourceIsValid() {
        assertEquals( true, ResourcesModelHelpersMethods.isValid( ResourcesFactory.createValidSampleResource() ) );
    }

    @Test
    public void resourceIsNotValid() {

    }
}
