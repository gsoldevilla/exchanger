package com.exchanger.api.exchangerapi.service;

import com.exchanger.api.exchangerapi.entity.database.Exchange;
import com.exchanger.api.exchangerapi.repository.ExchangeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private ExchangeRateService exchangeRateService;

    public Mono<Exchange> create(Exchange exchange) {
        return exchangeRepository
            .save(exchange)
            .zipWith(exchangeRateService.getById(exchange.getExchangeRateId()))
            .map(x -> Exchange
                .builder()
                .id(x.getT1().getId())
                .exchangeRateId(x.getT1().getExchangeRateId())
                .amount(x.getT1().getAmount())
                .finalAmount(x.getT1().getAmount().multiply(x.getT2().getValue()))
                .build()
            );
    }

    public Flux<Exchange> getAll() {
        return exchangeRepository.findAll();
    }
}
