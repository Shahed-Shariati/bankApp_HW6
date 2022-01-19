package service;

import model.Transaction;
import repository.TransactionRepository;

public class TransactionService {
    private TransactionRepository transactionRepository = new TransactionRepository();
    public void addTransaction(Transaction transaction){
        transactionRepository.addTransaction(transaction);
    }
}
