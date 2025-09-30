package com.example.demo.dto;

import com.example.demo.model.PurchaseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record PurchaseUpdateDTO(

        @NotNull
        @Min(1)
        Integer userId,
        @NotNull
        @Min(1)
        Integer productId,
        @Enumerated()
        PurchaseStatus purchaseStatus,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime purchaseDate

) {

}
