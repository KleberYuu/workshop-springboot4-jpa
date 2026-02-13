package com.estudosjava.curso.dto.validation;

import com.estudosjava.curso.dto.order.OrderRequestDTO;
import com.estudosjava.curso.dto.orderItem.OrderItemRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class NoDuplicateProductsValidator
        implements ConstraintValidator<NoDuplicateProducts, OrderRequestDTO> {

    @Override
    public boolean isValid(OrderRequestDTO dto, ConstraintValidatorContext context) {
        if (dto.getItems() == null) return true;

        Set<Long> productIds = new HashSet<>();
        for (OrderItemRequestDTO item : dto.getItems()) {
            Long productId = item.getProductId();

            if (productId == null) continue;

            if (!productIds.add(productId)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                                "Duplicate productId found: " + productId
                        )
                        .addPropertyNode("items")
                        .addConstraintViolation();

                return false;
            }
        }

        return true;
    }
}
