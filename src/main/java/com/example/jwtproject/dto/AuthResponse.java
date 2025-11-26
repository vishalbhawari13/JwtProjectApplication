package com.example.jwtproject.dto;

import lombok.Setter;

public class AuthResponse {

    @Setter
    private String token;
    private String tokenType = "Bearer";
    @Setter
    private String email;
    @Setter
    private String role;

    public AuthResponse() {
    }

    public AuthResponse(String token, String email, String role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }

    // Getters & Setters

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    // no setter for tokenType (always Bearer)

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

}
