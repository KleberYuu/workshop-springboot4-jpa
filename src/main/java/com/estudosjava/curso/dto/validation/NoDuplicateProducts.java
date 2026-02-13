package com.estudosjava.curso.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoDuplicateProductsValidator.class)
public @interface NoDuplicateProducts {

    String message() default "Duplicate products are not allowed in the order";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
