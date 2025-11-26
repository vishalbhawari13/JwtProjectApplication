package com.example.jwtproject.service;

import com.example.jwtproject.entity.UserAuth;
import com.example.jwtproject.exception.UserNotFoundException;
import com.example.jwtproject.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserAuthRepository userRepo;

    public UserAuth getCurrentUser()
    {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String email = principal.toString();

        return userRepo.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("User Not found with email : "+email));
    }

}
