package com.tech_challenge_fiap.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<Cpf, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        String digits = value.replaceAll("[^0-9]", "");
        return digits.length() == 11;
    }
}
