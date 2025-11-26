🚀 Spring Boot JWT Authentication & Authorization (MySQL + Spring Security)

A fully working JWT Authentication + Authorization system built using:

Spring Boot 4

Spring Security 7

JWT (jjwt 0.11.5)

MySQL

JPA / Hibernate

BCrypt Password Hashing

This project implements a clean, production-ready authentication system including:

User Registration

User Login

JWT Token Generation

JWT Validation

Custom JwtFilter

Secured APIs

Stateless Architecture

Input Validation

Clean Project Structure (Controller → Service → Repository → Entity → DTO → Security)

📁 Project Structure
src/main/java/com.example.jwtproject
│
├── controller
│   ├── auth
│   │   └── AuthController.java
│   └── user
│       └── UserController.java
│
├── config
│   └── SecurityConfig.java
│
├── dto
│   ├── LoginRequest.java
│   ├── RegisterRequest.java
│   └── AuthResponse.java
│
├── entity
│   └── UserAuth.java
│
├── exception
│   ├── GlobalExceptionHandler.java
│   └── UserNotFoundException.java
│
├── repository
│   └── UserAuthRepository.java
│
├── security
│   ├── JwtFilter.java
│   ├── JwtUtil.java
│   └── PasswordEncoderConfig.java
│
├── service
│   ├── AuthService.java
│   └── UserService.java
│
└── JwtProjectApplication.java

🛠️ Tech Stack
Layer	Technology
Backend	Spring Boot 4
Security	Spring Security 7
Auth	JWT (jjwt 0.11.5)
Database	MySQL
ORM	Spring Data JPA
Password Hash	BCryptEncoder
Build Tool	Maven
⚙️ MySQL Configuration

Add your DB config in application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/jwt_demo?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

🔐 Features Implemented
✔️ User Registration

Validates name, email, password

Encrypts password using BCrypt

Saves user in MySQL

Returns JWT token

✔️ Login

Checks user credentials

Validates password

Generates JWT with:

email

role

expiration time

✔️ JWT Security

Custom JwtFilter

Extracts token

Validates signature

Loads user details

Attaches authentication to SecurityContext

✔️ Protected APIs

Any endpoint outside /auth/** requires JWT.

🔒 API Endpoints
🔓 Public APIs (No JWT required)
1️⃣ Register
POST /auth/register


Body:

{
  "name": "Vishal",
  "email": "vishal@example.com",
  "password": "password123",
  "role": "USER"
}

2️⃣ Login
POST /auth/login


Body:

{
  "email": "vishal@example.com",
  "password": "password123"
}


Response contains:

{
  "token": "...",
  "tokenType": "Bearer",
  "email": "vishal@example.com",
  "role": "USER"
}

🔐 Protected API (JWT required)
3️⃣ Get Profile
GET /user/profile


Headers:

Authorization: Bearer <your_token>

🔄 Authentication Flow
1️⃣ User registers → backend saves encrypted password
2️⃣ User logs in → backend returns JWT
3️⃣ Client stores JWT
4️⃣ Client sends JWT in every request
5️⃣ JwtFilter validates the token
6️⃣ Controller executes only if token is valid

Fully stateless — no sessions.

🧪 Testing With Postman
1. Register

→ get JWT

2. Login

→ get new JWT

3. Call /user/profile

→ Must include token in header:

Authorization: Bearer <token>

4. Missing or wrong token

→ 401 Unauthorized (expected)
