package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank
        @Size(min = 6, max = 50, message = "O nome de usuario deve ter entre 6 e 50 caracteres")
        String username,

        @NotBlank
        @Size(min = 6, max = 30, message = "A senha deve ter entre 6 e 30 caracteres")
        String userpassword,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email) {
}
