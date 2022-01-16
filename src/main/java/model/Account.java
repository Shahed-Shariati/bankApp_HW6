package model;

public class Account {
 private int id;
 private Customer customer;
 private String accountNumber;
 private double balance;
 private BankBranch bankBranch;
 //todo quession
 private CreditCard creditCard;
 private String type;
 private boolean isActive;

public Account()
{}

 public Account(int id, Customer customer, String accountNumber, double balance, BankBranch bankBranch, String type) {
  this.id = id;
  this.customer = customer;
  this.accountNumber = accountNumber;
  this.balance = balance;
  this.bankBranch = bankBranch;
  this.type = type;
 }
}
