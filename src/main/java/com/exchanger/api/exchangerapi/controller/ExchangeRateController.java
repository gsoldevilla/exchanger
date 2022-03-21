package com.exchanger.api.exchangerapi.controller;

import com.exchanger.api.exchangerapi.entity.database.ExchangeRate;
import com.exchanger.api.exchangerapi.service.ExchangeRateService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/exchange-rates")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @PostMapping
    public Mono<ExchangeRate> create(@RequestBody @Valid ExchangeRate exchangeRate) {
        return exchangeRateService.create(exchangeRate);
    }

    @GetMapping
    public Flux<ExchangeRate> getAll() {
        return exchangeRateService.getAll();
    }
}
