package com.exchanger.api.exchangerapi.service;

import com.exchanger.api.exchangerapi.entity.database.User;
import com.exchanger.api.exchangerapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
