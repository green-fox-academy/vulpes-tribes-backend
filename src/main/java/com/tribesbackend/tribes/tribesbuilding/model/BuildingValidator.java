package com.tribesbackend.tribes.tribesbuilding.model;

import javafx.beans.binding.ObjectExpression;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;

public class BuildingValidator implements Validator {


    public boolean supports(Class clazz) {
        return Building.class.equals(clazz);
    }



    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "type", "type.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "level", "level.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "HP", "HP.required");
        Building b = (Building) obj;
        if (b.getLevel() <= 0) {
            e.rejectValue("level", "negativeValue");
        }
        if (b.getHP() < 0) {
            e.rejectValue("HP", "negative HP");
        }
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
        return null;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
        return null;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
        return null;
    }

    @Override
    public BeanDescriptor getConstraintsForClass(Class<?> clazz) {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> type) {
        return null;
    }

    @Override
    public ExecutableValidator forExecutables() {
        return null;
    }
}
