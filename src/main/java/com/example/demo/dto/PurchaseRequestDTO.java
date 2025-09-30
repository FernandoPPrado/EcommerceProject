package com.example.demo.dto;

import com.example.demo.model.PurchaseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PurchaseRequestDTO(

        @NotNull
        @Min(1)
        Integer userId,
        @NotNull
        @Min(1)
        Integer productId,
        @Enumerated()
        PurchaseStatus purchaseStatus
) {

}
