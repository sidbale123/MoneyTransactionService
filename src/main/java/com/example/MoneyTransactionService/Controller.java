package com.example.MoneyTransactionService;

import java.math.BigDecimal;
//import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.MoneyTransactionService.Entity.Account;
import com.example.MoneyTransactionService.Entity.Transaction;
import com.example.MoneyTransactionService.Service.AccountService;



@RestController
@RequestMapping("/transfer")
public class Controller {

	@Autowired
	public AccountService accountService;
	
	 @RequestMapping(method=RequestMethod.GET, value="/all")
	 public List<Account> all() {
	      return accountService.findAll();
	 }
	 
	 @RequestMapping(method=RequestMethod.POST,value="/create")
	  public Account create(@RequestBody Account account) {
	       return accountService.save(account);
	      //  return accountService.findAll();
	    }
	 @RequestMapping(method=RequestMethod.GET,value="/{accountNumber}")
	  public Account findAccount(@PathVariable String accountNumber) {
	       return accountService.findByAccountNumber(accountNumber);
	    
	    }
	 
	 @RequestMapping(method=RequestMethod.GET, value="/credit/{accountNumber}/{amount}")
	 public Account credit(@PathVariable String accountNumber, @PathVariable BigDecimal amount) {
		 return accountService.update(accountNumber, amount);
	 }
	 
	 @RequestMapping(method=RequestMethod.GET, value="/sendmoney/{accountNumber1}/{accountNumber2}/{amount}")
	  public Transaction sendMoney(@PathVariable String accountNumber1, @PathVariable String accountNumber2, @PathVariable BigDecimal amount)
	    {
		  // TransactionBalanceRequest transferBalanceRequest = new TransactionBalanceRequest(accountNumber1,accountNumber2,amount);
	       return accountService.sendMoney(accountNumber1,accountNumber2,amount); 
	       
	      
	    }
	 
	
	 @RequestMapping(method=RequestMethod.GET,value="/statement/{accountNumber}/{date}")
	    public AccountStatement getStatement(@PathVariable String accountNumber, @PathVariable String date )
	    {
	      
		 DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("yyyy-MM-d");
		  String str_date_1 = date;
          LocalDate local_date_1 = LocalDate.parse(str_date_1, formatter_1);
	        return accountService.getStatement(accountNumber, local_date_1);
	    } 
	 
	 @RequestMapping(method=RequestMethod.GET,value="/statement/{accountNumber}")
	    public AccountStatement getSimpleStatement(@PathVariable String accountNumber )
	    {
	  
	        return accountService.getSimpleStatement(accountNumber);
	    }  

}

/* @RequestMapping(method=RequestMethod.POST,value="/sendmoney")
public String sendMoney(@RequestBody TransactionBalanceRequest transferBalanceRequest)
  {
     Transaction t = accountService.sendMoney(transferBalanceRequest); 
     return t.getAccountNumber();
    
  }*/
