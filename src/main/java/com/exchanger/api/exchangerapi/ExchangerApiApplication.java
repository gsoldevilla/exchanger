package com.exchanger.api.exchangerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcAuditing
@EnableR2dbcRepositories
public class ExchangerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangerApiApplication.class, args);
	}
}
