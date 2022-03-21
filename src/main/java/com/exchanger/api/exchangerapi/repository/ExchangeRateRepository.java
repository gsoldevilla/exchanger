package com.exchanger.api.exchangerapi.repository;

import com.exchanger.api.exchangerapi.entity.database.ExchangeRate;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends R2dbcRepository<ExchangeRate, Integer> {
}
