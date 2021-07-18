package com.example.MoneyTransactionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MoneyTransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyTransactionServiceApplication.class, args);
	}

}
