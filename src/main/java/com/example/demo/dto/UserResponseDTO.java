package com.example.demo.dto;

import com.example.demo.model.Role;

public record UserResponseDTO(

        Long id,
        String username,
        String email,
        Role role) {
}
