package com.example.jwtproject.controller.auth;

import com.example.jwtproject.dto.AuthResponse;
import com.example.jwtproject.dto.LoginRequest;
import com.example.jwtproject.dto.RegisterRequest;
import com.example.jwtproject.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
