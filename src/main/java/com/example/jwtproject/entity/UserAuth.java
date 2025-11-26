package com.example.jwtproject.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users",uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;


}
