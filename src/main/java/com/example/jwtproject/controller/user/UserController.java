package com.example.jwtproject.controller.user;


import com.example.jwtproject.entity.UserAuth;
import com.example.jwtproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public UserAuth profile()
    {
       return userService.getCurrentUser();
    }
}
