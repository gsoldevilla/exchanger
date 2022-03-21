package com.exchanger.api.exchangerapi.repository;

import com.exchanger.api.exchangerapi.entity.database.User;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<User, Integer> {

    @Query("SELECT * FROM public.user WHERE username = :username")
    Mono<User> findByUsername(String username);
}
