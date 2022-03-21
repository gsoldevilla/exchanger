package com.exchanger.api.exchangerapi.service;

import com.exchanger.api.exchangerapi.entity.database.Currency;
import com.exchanger.api.exchangerapi.repository.CurrencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public Mono<Currency> create(Currency currency) {
        return currencyRepository.save(currency);
    }

    public Flux<Currency> getAll() {
        return currencyRepository.findAll();
    }
}
