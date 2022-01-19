package service;

import model.Account;
import model.CreditCard;
import repository.CreditCardRepository;

public class CreditCardService {
    private CreditCardRepository creditCardRepository = new CreditCardRepository();
    public int add(String numberCard,String expirDate,String cvv,String password,String passwordOnline)
    {
        int cvvInt = Integer.parseInt(cvv);
        CreditCard creditCard = new CreditCard(numberCard,expirDate,cvvInt,password,passwordOnline);
        return creditCardRepository.add(creditCard);
    }

    public CreditCard findByAccountId(int id){
        return creditCardRepository.findByAccountId(id);

    }

}
