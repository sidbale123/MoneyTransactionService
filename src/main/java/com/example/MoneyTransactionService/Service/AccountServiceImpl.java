package com.example.MoneyTransactionService.Service;

import java.math.BigDecimal;

//import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
//import java.util.Date;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.MoneyTransactionService.AccountStatement;

import com.example.MoneyTransactionService.Entity.Account;
import com.example.MoneyTransactionService.Entity.Transaction;
import com.example.MoneyTransactionService.Exception.AccountAlreadyExist;
import com.example.MoneyTransactionService.Exception.AccountDoesNotExist;
import com.example.MoneyTransactionService.Exception.BalanceCannotBeNegative;
import com.example.MoneyTransactionService.Exception.BankTransactionException;
import com.example.MoneyTransactionService.Repo.AccountRepository;
import com.example.MoneyTransactionService.Repo.TransactionRepository;

@Service
@Primary
public class AccountServiceImpl implements AccountService {
	

    @Autowired
    public AccountRepository AccRepository;
    
    @Autowired
    public TransactionRepository TransRepository;


	@Override
	public List<Account> findAll() {
		return AccRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor = { AccountAlreadyExist.class, BalanceCannotBeNegative.class })
	public Account save(Account account){
		if(AccRepository.findAccountNumber(account.getAccountNumber())) {
			  throw new AccountAlreadyExist();
		
		}else if(account.getCurrentBalance().compareTo(new BigDecimal(0)) < 0) {
			   throw new BalanceCannotBeNegative();
		 
		 }else 
		AccRepository.save(account);
        return AccRepository.findByAccountNumberEquals(account.getAccountNumber());
    }
	
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW )
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	public synchronized Account update(String accountNumber, BigDecimal amount) {
		 if(amount.compareTo(new BigDecimal(0)) < 0) {
			   throw new BalanceCannotBeNegative();
		 }
		 else if(!AccRepository.findAccountNumber(accountNumber)) {
			 throw new AccountDoesNotExist();
		 }
		Account ac = AccRepository.findByAccountNumberEquals(accountNumber);
		ac.setCurrentBalance(ac.getCurrentBalance().add(amount));
		AccRepository.save(ac);
		String DummyAccountNumber = "Manual Deposit";
		TransRepository.save(new Transaction(DummyAccountNumber,accountNumber,amount,new Timestamp(System.currentTimeMillis())));
		return ac;
	}
	
	@Override
	 @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
	 public Transaction sendMoney(String accountNumber1, String accountNumber2, BigDecimal amount1) throws BankTransactionException
	     {
		if(amount1.compareTo(new BigDecimal(0)) < 0) {
			   throw new BalanceCannotBeNegative();
		 }
		else if(!AccRepository.findAccountNumber(accountNumber1)) {
			 throw new AccountDoesNotExist();
		 }
		else if(!AccRepository.findAccountNumber(accountNumber2)) {
			 throw new AccountDoesNotExist();
		 }else {
	        String fromAccountNumber = accountNumber1;
	        String toAccountNumber = accountNumber2;
	        BigDecimal amount = amount1;
	        Account fromAccount = AccRepository.findByAccountNumberEquals(fromAccountNumber);
	        Account toAccount = AccRepository.findByAccountNumberEquals(toAccountNumber);
	        if(fromAccount.getCurrentBalance().compareTo(BigDecimal.ONE) == 1
	                && fromAccount.getCurrentBalance().compareTo(amount) == 1)
	        {
	            fromAccount.setCurrentBalance(fromAccount.getCurrentBalance().subtract(amount));
	            AccRepository.save(fromAccount);
	            toAccount.setCurrentBalance(toAccount.getCurrentBalance().add(amount));
	            AccRepository.save(toAccount);
	      
	            Transaction transaction = TransRepository.save(new Transaction(fromAccountNumber,toAccountNumber,amount, new Timestamp(System.currentTimeMillis())));
	            return transaction;
	        }
	        return null;
	    }
	     }
	 
	 @Override
	 @Transactional
	 public AccountStatement getStatement(String accountNumber, LocalDate date) {
		 if(!AccRepository.findAccountNumber(accountNumber)) {
			 throw new AccountDoesNotExist();
		 }
		 
	        Account account = AccRepository.findByAccountNumberEquals(accountNumber);
	      
	        AccountStatement as = new AccountStatement();
	        List<Transaction> TH = TransRepository.findTransactionByAccountNumberAndDate(accountNumber,date);
	        //for(Transaction  t : TH) {
	        //    System.out.println(t);
	        //}
	        as.setCurrentBalance(account.getCurrentBalance());
	        as.setTransactionHistory(TH);
	       // return new AccountStatement(account.getCurrentBalance(),TransRepository.findTransactionByAccountNumberAndDate(accountNumber, date));
	        return as;
	 }
	 
	 
	 @Override
	 public AccountStatement getSimpleStatement(String accountNumber) {
		 if(!AccRepository.findAccountNumber(accountNumber)) {
			 throw new AccountDoesNotExist();
		 }
		 
	        Account account = AccRepository.findByAccountNumberEquals(accountNumber);
	      
	     
	        return new AccountStatement(account.getCurrentBalance(),TransRepository.findTransactionByAccountNumber(accountNumber));
	        
	 }
	 
	 @Override
	public Account findByAccountNumber(String accountNumber) {
		return AccRepository.findByAccountNumberEquals(accountNumber);
	}

}
