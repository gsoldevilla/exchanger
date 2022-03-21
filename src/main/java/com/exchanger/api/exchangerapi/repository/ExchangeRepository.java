package com.exchanger.api.exchangerapi.repository;

import com.exchanger.api.exchangerapi.entity.database.Exchange;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends R2dbcRepository<Exchange, Integer> {
}
