package com.pk2024.backend.prediction_model;

import com.pk2024.backend.prediction_model.parameters.CityParameter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CityValidator implements ConstraintValidator<ValidCity, String> {

    private Set<String> acceptedValues;

    @Override
    public void initialize(ValidCity constraintAnnotation) {
        acceptedValues = Arrays.stream(CityParameter.values())
                .map(CityParameter::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && acceptedValues.contains(value.toLowerCase());
    }
}
