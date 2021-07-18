package com.example.MoneyTransactionService.Exception;

public class BankTransactionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	   public BankTransactionException(String message) {
	        super(message);
	    }

}
