package service;

import model.CreditCard;
import repository.CreditCardRepository;

import java.util.List;

public class CreditCardService {
    private CreditCardRepository creditCardRepository = new CreditCardRepository();
    public int add(String numberCard,String expirDate,String cvv,String password,String passwordOnline)
    {
        int cvvInt = Integer.parseInt(cvv);
        CreditCard creditCard = new CreditCard(numberCard,expirDate,cvvInt,password,passwordOnline);
        return creditCardRepository.add(creditCard);
    }
    public CreditCard findByCreditCardNumber(String cardNumber)
    {
        CreditCard creditCard = creditCardRepository.findByCardNumber(cardNumber);
        if(creditCard != null){
            return creditCard;
        }else {
            System.out.println("-------------------------- Not Found---------------------------");
        }
        return null;
    }
    public CreditCard findByAccountId(int id){
        CreditCard creditCard = creditCardRepository.findByAccountId(id);
        if(creditCard != null){
            return creditCard;
        }else {
            System.out.println("-------------------------- Not Found---------------------------");
        }
        return null;

    }
    public List<CreditCard> findByCustomerId(int id){

        return creditCardRepository.findByCustomerId(id);
    }
  public void upDate(CreditCard creditCard)
  {
      creditCardRepository.upDate(creditCard);
  }
}
