package com.example.MoneyTransactionService.Repo;

import java.time.LocalDate;
//import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.MoneyTransactionService.Entity.Transaction;

@Repository
@EnableJpaRepositories
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
     List<Transaction> findByFromAccountNumberEquals(String fromAccountNumber);
     List<Transaction> findBytoAccountNumberEquals(String toAccountNumber);
     
    @Query(value="select * from Transaction  where from_acc_no = :AccountNumber or to_acc_no = :AccountNumber", nativeQuery=true)
     List<Transaction> findTransactionByAccountNumber(@Param("AccountNumber") String AccountNumber);
     
     @Query(value="select * from Transaction  where (from_acc_no = :AccountNumber or to_acc_no = :AccountNumber) and date(trans_date_time) = :Date ", nativeQuery=true)
     List<Transaction> findTransactionByAccountNumberAndDate(@Param("AccountNumber") String AccountNumber,@Param("Date") LocalDate Date);
}
