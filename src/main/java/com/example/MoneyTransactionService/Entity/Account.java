package com.example.MoneyTransactionService.Entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;


@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="acc_id")
	private Long AccId;
	@Column(name= "acc_no")
    String accountNumber;
	 @Min(value = 0, message = "amount cannot be negative")
	 @Column(name="balance")
	BigDecimal currentBalance;
	
	 
	public Account() {}
	
	public Account( String accNo, BigDecimal accBalance) {
		
		accountNumber = accNo;
		currentBalance = accBalance;
	}
	

	public Long getAccId() {
		return AccId;
	}
	public void setAccId(Long accId) {
		AccId = accId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accNo) {
		accountNumber = accNo;
	}
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(BigDecimal currentBalance1) {
		currentBalance = currentBalance1;
	}

	@Override
	public String toString() {
		return "Account [AccId=" + AccId + ", accountNumber=" + accountNumber + ", currentBalance=" + currentBalance
				+ "]";
	}
	
	
}
