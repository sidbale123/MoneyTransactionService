package com.example.MoneyTransactionService.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.example.MoneyTransactionService.Entity.Account;

@SpringBootTest
class AccountServiceImplTest {
	
	@TestConfiguration
    static class AccountServiceTestContextConfiguration {
        @Bean
        public AccountServiceImpl accountServiceImplTest() {
            return new AccountServiceImpl();
        }
    }

	@Autowired
    private AccountServiceImpl accountService;
	
	@Test
	public void saveTest() {
		Account account1 = new Account( "170181", new BigDecimal(50000));
		accountService.save(account1);
		 BigDecimal bd1 =  accountService.findByAccountNumber(account1.getAccountNumber()).getCurrentBalance();
	       BigDecimal bd2 = new BigDecimal(50000);
	       assertEquals(bd1,bd2);
	}
	
	@Test
	@Transactional
	public void update() {
		Account account1 = new Account( "1018112", new BigDecimal(50000));
		  accountService.save(account1);
		  
		  accountService.update(account1.getAccountNumber(), new BigDecimal(3000));
		   BigDecimal bd1 =  accountService.findByAccountNumber(account1.getAccountNumber()).getCurrentBalance();
	        BigDecimal bd2 = new BigDecimal(53000);
	       assertEquals(bd1,bd2);
		  
	}

    @Test
    public void sendMoneyTest() {
    	
    	Account account1 = new Account( "108211", new BigDecimal(50000));
        Account account2 = new Account( "2008194", new BigDecimal(2000));
        accountService.save(account1);
        accountService.save(account2);

        
      /*  TransferBalanceRequest transferBalanceRequest =
                new TransferBalanceRequest(
                        account1.getAccountNumber(),
                        account2.getAccountNumber(),
                        new BigDecimal(3000)
                );*/
        accountService.sendMoney( account1.getAccountNumber(),
                account2.getAccountNumber(),
                new BigDecimal(3000));
      /*  assertThat(( accountService.findByAccountNumber(account1.getAccountNumber())
                .getCurrentBalance())
                .isEqualTo(new BigDecimal(47000)));*/
        BigDecimal bd1 =  accountService.findByAccountNumber(account1.getAccountNumber()).getCurrentBalance();
        BigDecimal bd2 = new BigDecimal(47000);
       assertEquals(bd1,bd2);
        
        BigDecimal bd3=  accountService.findByAccountNumber(account2.getAccountNumber()).getCurrentBalance();
        BigDecimal bd4 = new BigDecimal(5000);
        
        assertEquals(bd3,bd4);

   
    }
    

    @Test
    public void getStatement() {
    
        Account account1 = new Account( "1001783", new BigDecimal(50000));
        Account account2 = new Account( "2001884", new BigDecimal(2000));
        accountService.save(account1);
        accountService.save(account2);
     /*   TransferBalanceRequest transferBalanceRequest =
                new TransferBalanceRequest(
                        account1.getAccountNumber(),
                        account2.getAccountNumber(),
                        new BigDecimal(3000)
                );*/

        accountService.sendMoney( account1.getAccountNumber(),
                account2.getAccountNumber(),
                new BigDecimal(3000));
        /*assertThat(accountService.getSimpleStatement(account1.getAccountNumber())
                .getCurrentBalance())
                .isEqualTo(new BigDecimal(47000));*/
        BigDecimal bd5 = accountService.getSimpleStatement(account1.getAccountNumber()).getCurrentBalance();
        BigDecimal bd6 = new BigDecimal(47000);
        org.junit.Assert.assertEquals(bd5,bd6);

        accountService.sendMoney( account1.getAccountNumber(),
                account2.getAccountNumber(),
                new BigDecimal(3000));
        //assertThat(accountService.getSimpleStatement(account1.getAccountNumber())
          //      .getCurrentBalance()).isEqualTo(new BigDecimal(44000));
        BigDecimal bd7 = accountService.getSimpleStatement(account1.getAccountNumber()).getCurrentBalance();
        BigDecimal bd8 = new BigDecimal(44000);
        org.junit.Assert.assertEquals(bd7,bd8);

       // assertThat(accountService.getSimpleStatement(account1.getAccountNumber())
        //        .getTransactionHistory().size()).isEqualTo(2);
        
        org.junit.Assert.assertEquals(accountService.getSimpleStatement(account1.getAccountNumber()).getTransactionHistory().size(),2);
 
    }	            

}
/*	 assertThrows(NullPointerException.class,
 	            ()->{};*/

