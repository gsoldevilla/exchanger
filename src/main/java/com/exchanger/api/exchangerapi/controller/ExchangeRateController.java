package com.exchanger.api.exchangerapi.controller;

import com.exchanger.api.exchangerapi.entity.database.ExchangeRate;
import com.exchanger.api.exchangerapi.service.ExchangeRateService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
