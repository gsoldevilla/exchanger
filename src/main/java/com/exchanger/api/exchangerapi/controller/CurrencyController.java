package com.exchanger.api.exchangerapi.controller;

import com.exchanger.api.exchangerapi.entity.database.Currency;
import com.exchanger.api.exchangerapi.service.CurrencyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public Flux<Currency> getAll() {
        return currencyService.getAll();
    }
}
