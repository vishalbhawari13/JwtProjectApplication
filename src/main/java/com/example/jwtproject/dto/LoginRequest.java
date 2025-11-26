package com.example.jwtproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Email is req")
    @Email(message = "Email format not correct")
    private String email;

    @NotBlank(message = "Password req")
    private String password;

}
