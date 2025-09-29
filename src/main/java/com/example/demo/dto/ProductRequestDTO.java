package com.example.demo.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequestDTO(

        @NotBlank
        @Size(min = 6, max = 40, message = "O nome deve ter entre 6 e 40")
        String productName,

        @NotBlank
        @Size(min = 6, max = 120, message = "A descriçao deve ter entre 6 e 120 caracteres")
        String productDescription,

        @NotNull
        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal productPrice,

        @NotNull
        @Positive(message = "A quantidade em estoque deve ser maior que zero")
        Integer stockQuantity


) {
}
