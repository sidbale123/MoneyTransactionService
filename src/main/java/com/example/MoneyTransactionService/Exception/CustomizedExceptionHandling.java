package com.example.MoneyTransactionService.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BalanceCannotBeNegative.class)
    public ResponseEntity<Object> handleExceptions( BalanceCannotBeNegative exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Balance should be Positive");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        return entity;
    }
    
    @ExceptionHandler(AccountAlreadyExist.class)
    public ResponseEntity<Object> handleExceptions( AccountAlreadyExist exception, WebRequest webRequest){
    	 ExceptionResponse response = new ExceptionResponse();
         response.setDateTime(LocalDateTime.now());
         response.setMessage("Account with this account Number already exist");
         ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
         return entity;
    }
    
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,HttpHeaders headers,
    																	HttpStatus status, WebRequest webRequest){
    	return new ResponseEntity<Object>("Please change http method type", HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(AccountDoesNotExist.class)
    public ResponseEntity<Object> handleExceptions( AccountDoesNotExist exception, WebRequest webRequest){
    	 ExceptionResponse response = new ExceptionResponse();
         response.setDateTime(LocalDateTime.now());
         response.setMessage("Account with this account Number does not exist");
         ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
         return entity;
    }
    
    @ExceptionHandler(BankTransactionException.class)
    public ResponseEntity<Object> handleExceptions( BankTransactionException exception, WebRequest webRequest){
    	 ExceptionResponse response = new ExceptionResponse();
         response.setDateTime(LocalDateTime.now());
         response.setMessage("Something is WRONG.....");
         ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
         return entity;
    }
}
