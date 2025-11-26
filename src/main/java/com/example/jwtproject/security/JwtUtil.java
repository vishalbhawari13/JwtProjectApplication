package com.example.jwtproject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret key string (NOT base64). At least 32 chars for HS256.
    private static final String SECRET = "vishalSuperSecretKeyForJwtDemo123456";

    private static final long EXPIRATION_MS = 1000L * 60 * 60 * 24; // 24 hours

    private Key getSigningKey() {
        // Use raw bytes of the string as key, no Base64 decoding
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email, String role) {
        Date now = new Date();
        Date expiry = new Date(System.currentTimeMillis() + EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRole(String token) {
        Object role = getClaims(token).get("role");
        return role != null ? role.toString() : null;
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            Date exp = claims.getExpiration();
            return exp.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
