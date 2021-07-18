package com.example.MoneyTransactionService;

import java.math.BigDecimal;

import javax.validation.constraints.Min;

public class TransactionBalanceRequest {
	    private String fromAccountNumber;

	    private String toAccountNumber;
	    
	    @Min(value = 1, message = "amount should be positive")
	    private BigDecimal amount;

		public TransactionBalanceRequest() {
		}

		public TransactionBalanceRequest(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
			this.fromAccountNumber = fromAccountNumber;
			this.toAccountNumber = toAccountNumber;
			this.amount = amount;
		}

		public String getFromAccountNumber() {
			return fromAccountNumber;
		}

		public void setFromAccountNumber(String fromAccountNumber) {
			this.fromAccountNumber = fromAccountNumber;
		}

		public String getToAccountNumber() {
			return toAccountNumber;
		}

		public void setToAccountNumber(String toAccountNumber) {
			this.toAccountNumber = toAccountNumber;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		
		
		
	    
	    
}
