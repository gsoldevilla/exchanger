package com.exchanger.api.exchangerapi.service;

import com.exchanger.api.exchangerapi.entity.database.ExchangeRate;
import com.exchanger.api.exchangerapi.repository.ExchangeRateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public Mono<ExchangeRate> create(ExchangeRate exchangeRate) {
        return exchangeRateRepository.save(exchangeRate);
    }

    public Flux<ExchangeRate> getAll() {
        return exchangeRateRepository.findAll();
    }

    public Mono<ExchangeRate> getById(Integer id) {
        return exchangeRateRepository.findById(id);
    }
}
