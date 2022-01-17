package service;

import model.Account;
import repository.AccountRepository;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();

    public int add(String accountNumber,String balance)
    {
        Account account = new Account(accountNumber,Double.parseDouble(balance));
      return accountRepository.add(account);
    }

    public void setCreditCardId(int creditCardId,int accountId){
        accountRepository.setCreditCardId(creditCardId,accountId);
    }
}
