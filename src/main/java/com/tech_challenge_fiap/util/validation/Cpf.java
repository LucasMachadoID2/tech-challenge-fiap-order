package com.tech_challenge_fiap.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = CpfValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface Cpf {
    String message() default "CPF deve ter exatamente 11 dígitos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
