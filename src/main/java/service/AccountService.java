package service;

import model.Account;
import model.CreditCard;
import repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();
   private CreditCardService creditCardService = new CreditCardService();


    public List<Account> showCustomerAccount(String id){
        int customerID = Integer.parseInt(id);
        return accountRepository.showCustomerAccount(customerID);


    }
    public int add(String accountNumber,String balance)
    {
        try{
            Account account = new Account(accountNumber,Double.parseDouble(balance));
            return accountRepository.add(account);
        }catch (NumberFormatException e){
            System.out.println("your amount is wrong");
        }
       return 0;
       }

    public void setCreditCardId(int creditCardId,int accountId){
        accountRepository.setCreditCardId(creditCardId,accountId);
    }

    public List<Account> findByCustomerIdList(int id){
        List<Account> accounts = accountRepository.findByCustomerIdList(id);
        List<Account> accountList = new ArrayList<>();
        for (Account account:accounts) {
            CreditCard creditCard = creditCardService.findByAccountId(account.getId());
            account.setCreditCard(creditCard);
            accountList.add(account);
        }
        if(accountList.isEmpty()){
            System.out.println(" ------------------List is empty------------------------");
        }else {
            return accountList;
        }
        return null;
    }
    public Account findByCustomerId(int id){

          Account account = accountRepository.findByCustomerId(id);
          if(account != null) {
              CreditCard creditCard = creditCardService.findByAccountId(account.getId());
              account.setCreditCard(creditCard);
              return account;
          }else {
              System.out.println("Account not found");
          }
          return null;
    }
    public Account findByCardNumber(String cardNumber){
        return accountRepository.findByCardNumber(cardNumber);
    }
    public void upDate(Account account){
        accountRepository.upDate(account);
    }
}
