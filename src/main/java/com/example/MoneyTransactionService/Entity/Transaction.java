package com.example.MoneyTransactionService.Entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;


@Entity
@Table(name="transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trans_id")
	private Long TransId;
	@Column(name = "from_acc_no")
	private String fromAccountNumber;
	@Column(name = "to_acc_no")
	private String toAccountNumber;
	@Min(value = 0, message = "amount cannot be negative")
	@Column(name = "trans_amount")
	private BigDecimal TransAmount;
	@Column(name = "trans_date_time")
	private Timestamp TransDateTime;
	//@Column(name="Curr_balance")
	//private BigDecimal CurrBalance;
	
	public Transaction() {
	}
	
	public Transaction( String fromaccNo,String toaccNo, BigDecimal transAmount, Timestamp transDateTime) {
		
		fromAccountNumber = fromaccNo;
		toAccountNumber = toaccNo;
		TransAmount = transAmount;
		TransDateTime = transDateTime;
		//CurrBalance = currBalance;
	}

	public Long getTransId() {
		return TransId;
	}

	public void setTransId(Long transId) {
		TransId = transId;
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

	public BigDecimal getTransAmount() {
		return TransAmount;
	}

	public void setTransAmount(BigDecimal transAmount) {
		TransAmount = transAmount;
	}

	public Timestamp getTransDateTime() {
		return TransDateTime;
	}

	public void setTransDateTime(Timestamp transDateTime) {
		TransDateTime = transDateTime;
	}

	@Override
	public String toString() {
		return "Transaction [TransId=" + TransId + ", fromaccountNumber=" + fromAccountNumber + ", toaccountNumber="
				+ toAccountNumber + ", TransAmount=" + TransAmount + ", TransDateTime=" + TransDateTime + "]";
	}
	


	
	
	
	
}
