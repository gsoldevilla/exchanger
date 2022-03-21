package com.exchanger.api.exchangerapi.security;

import com.exchanger.api.exchangerapi.entity.database.User;

import com.exchanger.api.exchangerapi.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.joining;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {

    private static final String AUTHORITIES_KEY = "roles";

    private SecretKey secretKey;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        var secret = Base64.getEncoder().encodeToString(System.getenv("JWT_SECRET_KEY").getBytes());
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(Authentication authentication) {
        String username = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Claims claims = Jwts.claims().setSubject(username);

        if (!authorities.isEmpty()) {
            claims.put(AUTHORITIES_KEY, authorities.stream().map(GrantedAuthority::getAuthority).collect(joining(",")));
        }

        Date now = new Date();
        Date validity = new Date(now.getTime() + Long.parseLong(System.getenv("JWT_EXPIRATION_TIME")) * 1000);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        User principal = new User();

        principal.setId(1);
        principal.setUsername(claims.getSubject());
        principal.setPassword("$2a$10$gi1sxlepFRgjn7488Jkw3OCTL13ROxMdggaM6icALZhG9e4dbk3Ie");
        principal.setEmail("jgonzalezso@exchange.com.pe");

        return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);

            // parseClaimsJws will check expiration date, No need do here
            log.info("Expiration date: {}", claims.getBody().getExpiration());

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT: {}", e.getMessage());
            log.trace("Invalid JWT trace:", e);
        }

        return false;
    }
}
