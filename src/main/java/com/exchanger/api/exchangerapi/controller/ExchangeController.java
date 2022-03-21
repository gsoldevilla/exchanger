package com.exchanger.api.exchangerapi.controller;

import com.exchanger.api.exchangerapi.entity.database.Exchange;
import com.exchanger.api.exchangerapi.service.ExchangeService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/exchanges")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @PostMapping
    public Mono<Exchange> create(@RequestBody @Valid Exchange exchange) {
        return exchangeService.create(exchange);
    }

    @GetMapping
    public Flux<Exchange> getAll() {
        return exchangeService.getAll();
    }
}
