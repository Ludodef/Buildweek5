package it.epicode.azienda_energia.presentationlayer.utility;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EqualsToValidator implements ConstraintValidator<EqualsTo, String> {
    private String[] values;

    @Override
    public void initialize(EqualsTo constraintAnnotation) {
        this.values = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(String fieldValue, ConstraintValidatorContext context) {
        return fieldValue != null && Arrays.asList(values).contains(fieldValue);
    }
}