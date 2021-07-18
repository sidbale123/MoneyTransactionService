package com.example.MoneyTransactionService.Repo;





import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.MoneyTransactionService.Entity.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
	
	//@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	Account findByAccountNumberEquals(String AccountNumber);

	//@Query(value="SELECT acc_no FROM Account WHERE acc_no = :AccountNumber",nativeQuery=true)
	//String findAccountNumber(@Param("AccountNumber") String AccountNumber);
	

	//@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@Query(value="SELECT CASE WHEN EXISTS (SELECT * FROM account WHERE acc_no = :AccountNumber)THEN 'TRUE'ELSE 'FALSE'END",nativeQuery=true)
	Boolean findAccountNumber(@Param("AccountNumber") String AccountNumber);
	
	 
}
