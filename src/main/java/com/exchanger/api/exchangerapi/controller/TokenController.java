package com.exchanger.api.exchangerapi.controller;

import com.exchanger.api.exchangerapi.entity.api.CreateTokenRequest;
import com.exchanger.api.exchangerapi.entity.api.CreateTokenResponse;
import com.exchanger.api.exchangerapi.security.PBKDF2PasswordEncoder;
import com.exchanger.api.exchangerapi.service.UserService;
import com.exchanger.api.exchangerapi.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/tokens")
public class TokenController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PBKDF2PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping
    public Mono<ResponseEntity<CreateTokenResponse>> login(@RequestBody CreateTokenRequest request) {
        return userService.getByUsername(request.getUsername())
            .filter(userDetails -> passwordEncoder.encode(request.getPassword()).equals(userDetails.getPassword()))
            .map(userDetails -> ResponseEntity.ok(new CreateTokenResponse(jwtUtil.generateToken(userDetails))))
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
}
