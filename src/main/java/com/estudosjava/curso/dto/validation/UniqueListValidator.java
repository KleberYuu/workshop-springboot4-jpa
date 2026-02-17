package com.estudosjava.curso.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.List;

public class UniqueListValidator implements ConstraintValidator<UniqueList, List<?>> {

    @Override
    public boolean isValid(List<?> list, ConstraintValidatorContext context) {
        if (list == null) return true;
        return list.size() == new HashSet<>(list).size();
    }
}
