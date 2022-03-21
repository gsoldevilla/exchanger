package com.exchanger.api.exchangerapi.controller;

import com.exchanger.api.exchangerapi.entity.api.CreateTokenRequest;

import java.util.Map;

import javax.validation.Valid;

import com.exchanger.api.exchangerapi.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/tokens")
public class TokenController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ReactiveAuthenticationManager authenticationManager;

    @PostMapping
    public Mono<ResponseEntity<Map<String, String>>> create(@RequestBody @Valid Mono<CreateTokenRequest> request) {
        return request
            .flatMap(rq -> authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(rq.getUsername(), rq.getPassword()))
                .map(jwtProvider::createToken)
            )
            .map(jwt -> {
                HttpHeaders httpHeaders = new HttpHeaders();

                httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);

                Map<String, String> body = Map.of("access_token", jwt);

                return new ResponseEntity<>(body, httpHeaders, HttpStatus.OK);
            });
    }
}
