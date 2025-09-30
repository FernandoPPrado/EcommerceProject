package com.example.demo.dto;

import java.time.LocalDateTime;

public record PurchaseResponseDTO(

        Integer id,
        String username,
        String productName,
        String status,
        LocalDateTime orderDate

) {

}
