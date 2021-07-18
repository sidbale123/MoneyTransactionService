# MoneyTransactionService
money transaction Service
Simple Spring boot application which provide RESTful API for money transfer

Prerequisite
Maven
JDK 1.8+
MySQL
PostMan


In this project there are 7 API's

1) Create a account (localhost:8082/transfer/create)
2) find all the account(localhost:8082/transfer/all)
3) to credit an amount in a account(localhost:8082/transfer/credit/"accountNumber"/"amount")
4) to send money from one account to anther(localhost:8082/transfer/sendmoney/"accountNumber"/"accountNumber"/"amount")
5) to find an account with an account number(localhost:8082/transfer/"accountNumber")
6) to get satatement of a account with a account number(localhost:8082/transfer/statement/"accountNumber")
7) to get a statement of account on the basis of date (localhost:8082/transfer/statement/"accountNumber"/2021-07-18)
