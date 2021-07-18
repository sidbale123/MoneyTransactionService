package com.example.MoneyTransactionService;

import java.math.BigDecimal;
import java.util.List;

import com.example.MoneyTransactionService.Entity.Transaction;

public class AccountStatement {
	 BigDecimal currentBalance;
	 List<Transaction> transactionHistory;
	public AccountStatement() {
		
	}
	public AccountStatement(BigDecimal currentBalance, List<Transaction> transactionHistory) {
	
		this.currentBalance = currentBalance;
		this.transactionHistory = transactionHistory;
	} 
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}
	public List<Transaction> getTransactionHistory() {
		return transactionHistory;
	}
	public void setTransactionHistory(List<Transaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}
	
	@Override
	public String toString() {
		return "AccountStatement [currentBalance=" + currentBalance + ", transactionHistory=" + transactionHistory
				+ "]";
	}
	
	
	 
	
	 
}
