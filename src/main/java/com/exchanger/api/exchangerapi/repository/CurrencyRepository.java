package com.exchanger.api.exchangerapi.repository;

import com.exchanger.api.exchangerapi.entity.database.Currency;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends R2dbcRepository<Currency, Integer> {
}
