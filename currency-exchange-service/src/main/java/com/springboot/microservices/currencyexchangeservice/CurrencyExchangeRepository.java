package com.springboot.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.microservices.currencyexchangeservice.Bean.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

	CurrencyExchange findByFromAndTo(String from, String to);
	
}
