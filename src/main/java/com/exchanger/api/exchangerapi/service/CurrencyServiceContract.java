package com.exchanger.api.exchangerapi.service;

import com.exchanger.api.exchangerapi.entity.Currency;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrencyServiceContract {

    Mono<Currency> create(Currency currency);

    Flux<Currency> getAll();
}
