package com.exchanger.api.exchangerapi.config;

import com.exchanger.api.exchangerapi.repository.UserRepository;
import com.exchanger.api.exchangerapi.security.JwtWebFilter;
import com.exchanger.api.exchangerapi.security.JwtProvider;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    public final String PATH_CURRENCIES = "/*/currencies/**";
    public final String PATH_EXCHANGES = "/*/exchanges/**";
    public final String PATH_EXCHANGE_RATES = "/*/exchange-rates/**";
    public final String PATH_TOKENS = "/*/tokens/**";

    @Bean
    SecurityWebFilterChain springWebFilterChain(
        ServerHttpSecurity http,
        JwtProvider tokenProvider,
        ReactiveAuthenticationManager reactiveAuthenticationManager
    ) {
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .authenticationManager(reactiveAuthenticationManager)
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .authorizeExchange(x -> x
                .pathMatchers(HttpMethod.GET, PATH_CURRENCIES).authenticated()
                .pathMatchers(HttpMethod.POST, PATH_EXCHANGES).authenticated()
                .pathMatchers(HttpMethod.POST, PATH_EXCHANGE_RATES).authenticated()
                .pathMatchers(HttpMethod.POST, PATH_TOKENS).permitAll()
                .anyExchange().permitAll()
            )
            .addFilterAt(new JwtWebFilter(tokenProvider), SecurityWebFiltersOrder.HTTP_BASIC)
            .build();
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(
        ReactiveUserDetailsService userDetailsService,
        PasswordEncoder passwordEncoder
    ) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(
            userDetailsService
        );

        authenticationManager.setPasswordEncoder(passwordEncoder);

        return authenticationManager;
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(UserRepository users) {
        return username -> users
            .findByUsername(username)
            .map(u -> User
                .withUsername(u.getUsername()).password(u.getPassword())
                .authorities(u.getRoles().toArray(new String[0]))
                .accountExpired(!u.isActive())
                .credentialsExpired(!u.isActive())
                .disabled(!u.isActive())
                .accountLocked(!u.isActive())
                .build()
            );
    }
}
