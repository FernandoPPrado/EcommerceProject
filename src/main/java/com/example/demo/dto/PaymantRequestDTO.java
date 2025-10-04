package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PaymantRequestDTO(

        @NotNull
        @Positive
        Integer productId,

        @NotNull
        @Positive
        int quantity
) {


}
