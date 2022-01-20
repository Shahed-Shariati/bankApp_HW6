package service;

import model.Account;
import model.Transaction;
import repository.TransactionRepository;
import utility.AccountNotFound;
import utility.MoneyNotEnough;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class TransactionService {
    private TransactionRepository transactionRepository = new TransactionRepository();
    private AccountService accountService = new AccountService();
    LocalDate localDate = LocalDate.now();
    public void addTransaction(Transaction transaction){
        transactionRepository.addTransaction(transaction);
    }

      public void withdraw(double amount, String cardNumberSource,String cardNumberDestination) throws ParseException {
          Date today = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          Account accountsource = accountService.findByCardNumber(cardNumberSource);
          Account accountDestination = accountService.findByCardNumber(cardNumberDestination);

          if(accountsource == null){
              throw new AccountNotFound("------------------Account not found-------------------");
          }else if (accountsource.getBalance() < amount){
               throw new MoneyNotEnough("------------------Money not enough--------------------");
          }else {
              Transaction transaction = new Transaction(accountsource,amount,"Pending","withdraw",today,accountDestination);
              accountsource.setBalance(accountsource.getBalance() - amount);
              accountService.upDate(accountsource);
              addTransaction(transaction);
          }
      }

      public void deposit(double amount, String cardNumberSource,String cardNumberDestination) throws ParseException {
          Date today = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          Account accountsource = accountService.findByCardNumber(cardNumberSource);
          Account accountDestination = accountService.findByCardNumber(cardNumberDestination);
          if(accountDestination == null){
              throw new AccountNotFound("------------------Account not found-------------------");
          }else {
              Transaction transaction = new Transaction(accountDestination,amount,"Pending","deposit",today,accountsource);
              accountDestination.setBalance(accountDestination.getBalance() + amount);
              accountService.upDate(accountDestination);
              addTransaction(transaction);
          }

      }
}
