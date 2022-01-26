package model;

import utility.ValidationDigitAccountNumber;

public class Account {
 private int id;
 private Customer customer;
 private String accountNumber;
 private double balance;
 private BankBranch bankBranch;
 //todo quession
 private CreditCard creditCard;
 private String type;
 private int typeId;

 public int getTypeId() {
  return typeId;
 }

 public Account()
{}

 public Account(String accountNumber, double balance) {
  validationAccountNumber(accountNumber);
  this.accountNumber = accountNumber;
  this.balance = balance;
 }

 public Account(int id, String accountNumber, double balance, CreditCard creditCard) {
  validationAccountNumber(accountNumber);
  this.id = id;
  this.accountNumber = accountNumber;
  this.balance = balance;
  this.creditCard = creditCard;
 }

 public Account(int id, String accountNumber, double balance) {
  validationAccountNumber(accountNumber);
  this.id = id;
  this.accountNumber = accountNumber;
  this.balance = balance;
 }

 public Account(int id, Customer customer, String accountNumber, double balance, BankBranch bankBranch, String type) {
  validationAccountNumber(accountNumber);
  this.id = id;
  this.customer = customer;
  this.accountNumber = accountNumber;
  this.balance = balance;
  this.bankBranch = bankBranch;
  this.type = type;
 }

 public CreditCard getCreditCard() {
  return creditCard;
 }

 public String getType() {
  return type;
 }

 public String getAccountNumber() {
  return accountNumber;
 }

 public void setAccountNumber(String accountNumber) {
  this.accountNumber = accountNumber;
 }

 public double getBalance() {
  return balance;
 }

 public void setBalance(double balance) {
  this.balance = balance;
 }

 public int getId() {
  return id;
 }

 public void setCreditCard(CreditCard creditCard) {
  this.creditCard = creditCard;
 }

 @Override
 public String toString() {
  return "Account{" +
          "id=" + id +
          ", accountNumber='" + accountNumber + '\'' +
          ", balance=" + balance +
          ", creditCard=" + creditCard +
          '}';
 }
 private void validationAccountNumber(String accountNumber){
  char[] nationalCodeArray = accountNumber.toCharArray();

   for (char c : nationalCodeArray) {
    if (Character.isDigit(c)) {

    } else {
     throw new ValidationDigitAccountNumber("phone is not digit");
    }
   }

 }

}
