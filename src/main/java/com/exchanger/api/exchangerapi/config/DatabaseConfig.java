package com.exchanger.api.exchangerapi.config;

import com.exchanger.api.exchangerapi.entity.database.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;

@Configuration
@EnableR2dbcAuditing
@EnableR2dbcRepositories
public class DatabaseConfig {

    @Bean
    ReactiveAuditorAware<Integer> auditorAware() {
        return () -> ReactiveSecurityContextHolder.getContext()
            .map(SecurityContext::getAuthentication)
            .filter(Authentication::isAuthenticated)
            .map(Authentication::getPrincipal)
            .map(User.class::cast)
            .map(User::getId);
    }
}
