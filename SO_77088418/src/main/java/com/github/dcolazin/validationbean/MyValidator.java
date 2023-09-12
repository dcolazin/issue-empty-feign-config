package com.github.dcolazin.validationbean;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyValidator implements ConstraintValidator<MyAnnotation, String> {

    @Autowired
    private MyProperties properties;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return properties.getAllowedValues().contains(value);
    }
}
