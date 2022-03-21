package com.exchanger.api.exchangerapi.util;

import com.exchanger.api.exchangerapi.entity.database.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import java.security.Key;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final String secret = System.getenv("JWT_SECRET_KEY");
    private final String expirationTime = System.getenv("JWT_EXPIRATION_TIME");
    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("role", user.getRoles());

        return doGenerateToken(claims, user.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        long expirationTimeLong = Long.parseLong(expirationTime); // In seconds
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);

        return Jwts
            .builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(createdDate)
            .setExpiration(expirationDate)
            .signWith(key)
            .compact();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
