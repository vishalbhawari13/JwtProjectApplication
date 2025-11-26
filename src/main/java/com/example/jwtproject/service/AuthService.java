package com.example.jwtproject.service;

import com.example.jwtproject.dto.AuthResponse;
import com.example.jwtproject.dto.LoginRequest;
import com.example.jwtproject.dto.RegisterRequest;
import com.example.jwtproject.entity.UserAuth;
import com.example.jwtproject.exception.UserNotFoundException;
import com.example.jwtproject.repository.UserAuthRepository;
import com.example.jwtproject.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserAuthRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        String role = request.getRole();
        if (role == null || role.isBlank()) {
            role = "USER";
        }

        UserAuth user = new UserAuth();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role.toUpperCase());

        UserAuth saved = userRepo.save(user);

        String token = jwtUtil.generateToken(saved.getEmail(), saved.getRole());

        return new AuthResponse(token, saved.getEmail(), saved.getRole());
    }

    public AuthResponse login(LoginRequest request) {
        UserAuth user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UserNotFoundException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return new AuthResponse(token, user.getEmail(), user.getRole());
    }
}
