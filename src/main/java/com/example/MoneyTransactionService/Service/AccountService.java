package com.example.MoneyTransactionService.Service;

import java.math.BigDecimal;
//import java.sql.Date;
import java.time.LocalDate;
//import java.sql.Timestamp;
import java.util.List;

import com.example.MoneyTransactionService.AccountStatement;

import com.example.MoneyTransactionService.Entity.Account;
import com.example.MoneyTransactionService.Entity.Transaction;


public interface AccountService {
	   List<Account> findAll();
	    Account save(Account account);
	    Account update(String accountNumber, BigDecimal amount);
	   // Transaction sendMoney(TransactionBalanceRequest transferBalanceRequest);
	    Transaction sendMoney(String accountNumber1, String accountNumber2, BigDecimal amount);
	    AccountStatement getStatement(String accountNumber, LocalDate date);
		//AccountStatement getStatement(String accountNumber, java.util.Date date);
		AccountStatement getSimpleStatement(String accountNumber);
		Account findByAccountNumber(String accountNumber);
	  
}
