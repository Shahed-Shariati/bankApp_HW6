package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.*;
import org.postgresql.jdbc.UUIDArrayAssistant;
import service.*;

public class RunApplication {
    private UserService userService = new UserService();
    private TransactionService transactionService = new TransactionService();
    ApplicationService applicationService = new ApplicationService();
    private AccountService accountService = new AccountService();
    private AccountCustomerService accountCustomerService = new AccountCustomerService();
    private CreditCardService creditCardService = new CreditCardService();
    private CustomerService customerService = new CustomerService();
 private Scanner input = new Scanner(System.in);

public void runApplication() throws ParseException {
    while (true){
       Menu.loginMenu();
       System.out.println("Choice");
       String input = getUserInput().trim();
       if(input.equals("1")){
         login();
       }else if(input.equals("2"))
       {

       }else if(input.equals("3")){
           break;
       }else {
           System.out.println();
       }
    }
}

private void singUp(){
    System.out.println("_________________________Sing up---------------------------");
    System.out.println("1:Boss");
    System.out.println("2:Customer");
    System.out.println("3:Clerk");
    System.out.println("Choice your Role");
    String role = getUserInput();
    System.out.println("Enter your name");
    String firstName = getUserInput();
    System.out.println("Enter your Last Name");
    String LastName = getUserInput();
    System.out.println("enter your national code");
    String nationalCode = getUserInput();
    System.out.println("enter your phone number");
    String phoneNumber = getUserInput();
    System.out.println("enter your address");
    String address = getUserInput();
    System.out.println("Enter your username: ");
    String username = getUserInput();
    System.out.println("Enter your password: ");
    String password = getUserInput();


}

private void login() throws ParseException {
    System.out.println("Enter user name and password");
    String[] input = getUserInput().trim().split(" +");
    if(input[0].equalsIgnoreCase("back")){
        System.out.println();
    }else if(input.length == 2){
        User user = applicationService.login(input[0], input[1]);
        if(user == null){
            System.out.println("Your user name or password is wrong");
        }else if(user.getRole() == 1){
            System.out.println(user.getPassWord());
        }else if(user.getRole() == 2){
           Customer customer = findCustomer(user.getId());
           customerMenu(customer);
        }else if(user.getRole() == 3){
           clerkMenu();
        }

    }else {
        System.out.println();
    }
}

    private void customerMenu(Customer customer) throws ParseException {

        while (true){
            Menu.customerMenu();
            String input = getUserInput();
            if(input.equals("1")){
                customerService.showCustomerAccount(String.valueOf (customer.getId()));
                 transaction(customer.getId(),customer);
            }else if(input.equals("2")) {
                showCustomerCreditCard(customer);
                transaction(customer.getId(),customer);
            }else if(input.equals("3")){
                showCustomerCreditCard(customer);
                changePassword();
            }else if(input.equals("5")) {
                break;
            }else {
                System.out.println("Wrong choice");
            }
        }

    }

    private void clerkMenu(){
    while (true) {
        Menu.clerkMenu();
        System.out.println("Choice ");
        String input = getUserInput();
        if(input.equals("1")){
          int customerid = addCustomer();
            System.out.println("------------------------create Account----------------------------------");
          int accountId = addAccount();
          addAccountCustomer(accountId,customerid);

          System.out.println("--------------------------create credit card------------------------------");

           int creditCardId = addCreditCard();
          setCreditCardId(creditCardId,accountId);

            System.out.println("------------------------------success--------------------------------------");
        }else if(input.equals("2")){
            showCustomer();
            System.out.println("Chioce customer id");
            int customerId = Integer.parseInt(getUserInput());
            int accountId = addAccount();
            addAccountCustomer(accountId,customerId);
            int creditCardId = addCreditCard();
            setCreditCardId(creditCardId,accountId);

        }else if(input.equals("3")){
            System.out.println("transaction");
        }else if(input.equals("4")){
            System.out.println(4);
        }else if(input.equals("5")) {
           showCustomerAccount();

        }else if(input.equals("6")){
             break;
        }else if(input.equals("7")) {
            break;
        }else {
            System.out.println("Wrong choice");
        }
    }
    }
   private void showCustomerCreditCard(Customer customer){
       List<CreditCard> creditCards = creditCardService.findByCustomerId(customer.getId());
       for (CreditCard credit:creditCards  ) {
           System.out.println(credit);
       }
   }
   private void changePassword()
   {
       CreditCard creditCard;
       System.out.println("Choice Credit Card\n Example(1111-1111-1111-1111)");
       String creditCardNumber = getUserInput();
       if(Validation.cardNumber(creditCardNumber)){
           creditCardNumber = Validation.cardNumberReplace(creditCardNumber);
           creditCard = creditCardService.findByCreditCardNumber(creditCardNumber);
           System.out.println("1:Change Password");
           System.out.println("2:Change Password Online");
           String input = getUserInput();
           if (input.equals("1")){
               System.out.println("Enter your old password");
               System.out.println(creditCard.getExpireDate());
               String oldPassword = getUserInput();
               System.out.println("Enter your new password:(max 4 )");
               String newPassword = getUserInput();
               if(creditCard.getPassword().equals(oldPassword)){
                   creditCard.setPassword(newPassword);
                   setNewPassword(creditCard);
               }else {
                   System.out.println("--------------------Your password is Wrong-----------------------");
               }
           }else if (input.equals("2")){
               System.out.println("Enter your old password");
               System.out.println(creditCard.getExpireDate());
               String oldPassword = getUserInput();
               System.out.println("Enter your new password online:");
               String newPassword = getUserInput();
               if(creditCard.getPassword().equals(oldPassword)){
                   creditCard.setPasswordOnline(newPassword);
                   setNewPassword(creditCard);
               }else {
                   System.out.println("--------------------Your password is Wrong-----------------------");
               }
           }else {
               System.out.println("your Choice is wrong");
           }

       }



       System.out.println("");


   }

    private void setNewPassword(CreditCard creditCard){
       creditCardService.upDate(creditCard);
    }
    private int addCreditCard()
    {
        while (true) {
        System.out.println("Enter card number");
        String numberCard = getUserInput();
        System.out.println("Enter expire date:");
        String expireDate = getUserInput();
        System.out.println("Enter cvv");
        String cvv = getUserInput();
        System.out.println("Enter password card");
        String password = getUserInput();
        System.out.println("Enter onlinePassword");
        String passwordOnlinbe = getUserInput();
        if (Validation.cardNumber(numberCard)) {
            numberCard = Validation.cardNumberReplace(numberCard);
            return creditCardService.add(numberCard, expireDate, cvv, password, passwordOnlinbe);
        }else {
            System.out.println("cardNumber is Wrong");
        }

        }
    }


    private int addAccount(){
        System.out.println("Enter Account number:");
        String accountNumber = getUserInput();
        System.out.println("Enter  amount ");
        String balance = getUserInput();
        balance = Validation.amount(balance);
     return accountService.add(accountNumber,balance);
     }
    private void setCreditCardId(int creditCardId,int accountId)
    {
        accountService.setCreditCardId(creditCardId,accountId);
    }
    private void showCustomerAccount(){
        showCustomer();
        System.out.println("Choice customer id");
        String id = getUserInput();
        customerService.showCustomerAccount(id);
    }
    private void showCustomer(){
       customerService.showCustomers();
    }
    private void addAccountCustomer(int accountId,int customerId)
     {
         accountCustomerService.add(accountId,customerId);
     }

    private int addCustomer()
    {
        System.out.println("Enter customer name");
        String firstName = getUserInput();
        System.out.println("Enter customer Last Name");
        String lastName = getUserInput();
        System.out.println("enter customer national code");
        String nationalCode = getUserInput();
        System.out.println("enter customer phone number");
        String phoneNumber = getUserInput();
        System.out.println("enter customer address");
        String address = getUserInput();
        System.out.println("Enter customer username: ");
        String username = getUserInput();
        System.out.println("Enter customer password: ");
        String password = getUserInput();
        return userService.add(firstName,lastName,nationalCode,phoneNumber,address,username,password,"2");

    }


    public void transaction(int id, Customer customer) throws ParseException {

        CreditCard creditCardSource;
        CreditCard creditCardDestination;
        LocalDate localDate = LocalDate.now();

   while (true)
   {
     System.out.println("Enter back to return menu");
     System.out.println("Choice your account");

     String cardNumberSource = getUserInput();
     if(cardNumberSource.equalsIgnoreCase("back"))
     {
         break;
     }
     if(Validation.cardNumber(cardNumberSource))
     {
         cardNumberSource = Validation.cardNumberReplace(cardNumberSource);
         System.out.println("enter your cvv");
         String cvv = getUserInput();
         System.out.println("enter your expire date");
         String expireDate = getUserInput();
         System.out.println("Enter amount:");
         String amount = getUserInput();
         System.out.println("Enter your password online");
         String passwordOnline = getUserInput();
         System.out.println("Enter Destinaction card number");
         String cardNumberDestination = getUserInput();

         CreditCard cardCheck = new CreditCard(cardNumberSource,expireDate,Integer.parseInt(cvv),null,passwordOnline);
         if(Validation.cardNumber(cardNumberDestination))
         {
             cardNumberSource = Validation.cardNumberReplace(cardNumberSource);
             creditCardSource =  creditCardService.findByCreditCardNumber(cardNumberSource);
             creditCardDestination = creditCardService.findByCreditCardNumber(cardNumberDestination);
             if(creditCardSource != null && creditCardDestination != null)
             {
                if(checkCard(creditCardSource,cardCheck)){
                    Account accountsource = accountService.findByCardNumber(cardNumberSource);
                    Account accountDestination = accountService.findByCardNumber(cardNumberDestination);
                    accountsource.setBalance( accountsource.getBalance() - Double.parseDouble(amount));
                    accountDestination.setBalance(accountDestination.getBalance() + Double.parseDouble(amount));
                    accountService.upDate(accountsource);
                    accountService.upDate(accountDestination);


                    Date today = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
                    Transaction transaction = new Transaction(accountsource,Double.parseDouble(amount),"Pending","withdraw",today,accountDestination);
                    Transaction transaction1 = new Transaction(accountDestination,Double.parseDouble(amount),"Pending","deposit",today,accountsource);
                    addTransaction(transaction);
                    addTransaction(transaction1);
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("-------------------------------------------------------------------------");
                }


             }
         }else
             {
             System.out.println("---------your card number not valid----------------");
            }
     }else
         {
         System.out.println("---------your card number not valid----------------");
        }


    }

    }
    private void addTransaction(Transaction transaction){
     transactionService.addTransaction(transaction);
    }
    private Boolean checkCard(CreditCard creditCard,CreditCard cardCheck){
        LocalDate localDate = LocalDate.now();
        try {
            Date today = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(creditCard.getExpireDate());
            if(creditCard.getPasswordOnline().equals(cardCheck.getPasswordOnline()) && creditCard.getCvv() == cardCheck.getCvv() && today.before(date1) && creditCard.getExpireDate().equals(cardCheck.getExpireDate())){
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
    private String getUserInput()
    {
        return input.nextLine().trim();
    }
    private Customer findCustomer(int id)
    {
        return customerService.findById(id);

    }
}
