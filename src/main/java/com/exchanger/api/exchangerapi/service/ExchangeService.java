package com.exchanger.api.exchangerapi.service;

import com.exchanger.api.exchangerapi.entity.database.Exchange;
import com.exchanger.api.exchangerapi.repository.ExchangeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    public Mono<Exchange> create(Exchange exchange) {
        return exchangeRepository.save(exchange);
    }
}
