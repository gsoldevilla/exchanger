package com.exchanger.api.exchangerapi.repository;

import com.exchanger.api.exchangerapi.entity.Currency;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface CurrencyRepository extends ReactiveCrudRepository<Currency, Integer> {

    Mono<Currency> findByCode(String code);

    Mono<Currency> findByName(String name);
}
