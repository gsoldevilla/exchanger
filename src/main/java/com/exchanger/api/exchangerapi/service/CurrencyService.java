package com.exchanger.api.exchangerapi.service;

import com.exchanger.api.exchangerapi.entity.Currency;
import com.exchanger.api.exchangerapi.repository.CurrencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrencyService implements CurrencyServiceContract {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Mono<Currency> create(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public Flux<Currency> getAll() {
        return currencyRepository.findAll();
    }
}
