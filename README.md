🚀 Spring Boot JWT Authentication API

A complete JWT Authentication & Authorization backend built with Spring Boot 4, Spring Security 7, MySQL, and JPA.
Implements a fully secure, production-grade login/register system with token validation, password encryption, and protected routes.

🌟 Features
🔐 Authentication

User Registration (Signup)

User Login

Password Encryption using BCrypt

JWT Token Generation

JWT Validation

🛡️ Authorization

/auth/** → Public

All other endpoints → Require JWT

Stateless architecture (No sessions)

Custom JwtFilter to validate token on each request

🗄️ Database (MySQL/Postgres)

Auto table creation with Hibernate

Unique email constraint

Stores encrypted passwords only

🧱 Tech Stack
Layer	Technology
Backend Framework	Spring Boot 4
Security	Spring Security 7
Authentication	JWT (jjwt 0.11.5)
Database	MySQL
ORM	Spring Data JPA
Password Hashing	BCrypt
Build Tool	Maven
📁 Project Structure
src/main/java/com.example.jwtproject
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

⚙️ Configuration
application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/jwt_demo?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

🔥 API Endpoints
🔓 Public Routes (No JWT Required)
1️⃣ Register User
POST /auth/register


Request Body:

{
  "name": "Vishal",
  "email": "vishal@example.com",
  "password": "password123",
  "role": "USER"
}

2️⃣ Login User
POST /auth/login


Request Body:

{
  "email": "vishal@example.com",
  "password": "password123"
}


Response:

{
  "token": "eyJh...",
  "tokenType": "Bearer",
  "email": "vishal@example.com",
  "role": "USER"
}

🔐 Protected Routes (JWT Required)
3️⃣ Get Profile
GET /user/profile


Headers:

Authorization: Bearer <jwt_token>

🔄 JWT Authentication Flow

Register: Save new user → BCrypt encrypt password

Login: Validate user → Create JWT

Client stores token

Each API call uses:

Authorization: Bearer <token>


JwtFilter validates token

If valid → Request allowed

If invalid/missing → 401 Unauthorized

🧪 Testing (Postman)
✔️ Register → Get Token
✔️ Login → Get Token
✔️ Use Token in
Authorization: Bearer <token>

✔️ Call Protected API
✔️ Try without token → Should get 401
✔️ Try with invalid token → Should get 403
📌 Common Errors & Fixes
❌ Illegal base64 character '_'

Fix: Use Keys.hmacShaKeyFor(secret.getBytes())

❌ No property existByEmail found

Fix: Rename to:

boolean existsByEmail(String email);

❌ JWT token parsing error

Fix: Use jjwt version 0.11.5

🎯 What You Learned

End-to-end JWT Security

Stateless Authentication Architecture

Custom Spring Security Filter

Secure API Design

BCrypt password hashing

MySQL integration

Clean Controller → Service → Repository design

🛠️ Next Enhancements (Optional)

You can extend this project with:

🔹 Role-Based Access

/admin/** → only ADMIN

🔹 Refresh Tokens

Long-term authentication

🔹 Logout (Blacklist JWT)
🔹 Change Password / Update Profile
🔹 Swagger Documentation
🔹 React.js Frontend Integration
🤝 Contributing

PRs and improvements are always welcome!

📜 License

This project is open-source under the MIT License.
