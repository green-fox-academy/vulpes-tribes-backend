package com.tribesbackend.tribes.models;

import com.tribesbackend.tribes.factories.BattlesFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;

public class BattleTest {
    BattlesFactory battlesFactory = new BattlesFactory();

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
    public void battleModelViolationsTest(){
        Battle epicBattle = battlesFactory.createOneBattle();
        Set<ConstraintViolation<Battle>> violations
                = validator.validate(epicBattle);
        assertTrue(violations.isEmpty());
        assertEquals(3, epicBattle.attackerTroops.size());
    }
}
