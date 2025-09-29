package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductResponseDTO(

        Integer id,
        String productName,
        String productDescription,
        BigDecimal productPrice,
        Integer stockQuantity
) {
}
