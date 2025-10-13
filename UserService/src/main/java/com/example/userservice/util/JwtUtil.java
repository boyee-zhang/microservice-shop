package com.example.userservice.util;

import com.example.userservice.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.security.Key;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

@Component
public class JwtUtil {
        private final Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        private final long EXPIRATION = 86400000;

        public String generateToken(String username, Role role) {
            return Jwts.builder()
                    .setSubject(username)
                    .claim("role", role)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                    .signWith(signingKey, SignatureAlgorithm.HS512)
                    .compact();
        }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return (String) extractAllClaims(token).get("role");
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
