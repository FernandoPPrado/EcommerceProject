package com.example.demo.dto;

import java.time.LocalDateTime;

public record PurchaseRequest(

        Integer id,
        String username,
        String productName,
        String status,
        LocalDateTime orderDate

) {

}
