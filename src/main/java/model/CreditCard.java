package model;

public class CreditCard {
    private int id;
    private Account account;
    private String numberCard;
    private String expireDate;
    private int cvv;
    private String password;
    private String passwordOnline;
    private boolean isActive;
    private int isAvtiveInt;

    public CreditCard( String numberCard, String expireDate, int cvv, String password, String passwordOnline, int isAvtiveInt) {

        this.numberCard = numberCard;
        this.expireDate = expireDate;
        this.cvv = cvv;
        this.password = password;
        this.passwordOnline = passwordOnline;
        this.isAvtiveInt = isAvtiveInt;
    }

    public CreditCard(int id, String numberCard, String expireDate, int cvv, String passwordOnline, int isAvtiveInt) {
        this.id = id;
        this.numberCard = numberCard;
        this.expireDate = expireDate;
        this.cvv = cvv;
        this.passwordOnline = passwordOnline;
        this.isAvtiveInt = isAvtiveInt;
    }

    public CreditCard(int id, Account account, String numberCard, String expireDate, int cvv, String password, String passwordOnline, boolean isActive) {
        this.id = id;
        this.account = account;
        this.numberCard = numberCard;
        this.expireDate = expireDate;
        this.cvv = cvv;
        this.password = password;
        this.passwordOnline = passwordOnline;
        this.isActive = isActive;
    }

    public CreditCard(String numberCard, String expireDate, int cvv, String password, String passwordOnline) {
        this.numberCard = numberCard;
        this.expireDate = expireDate;
        this.cvv = cvv;
        this.password = password;
        this.passwordOnline = passwordOnline;
    }

    public int getId() {
        return id;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public int getCvv() {
        return cvv;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordOnline() {
        return passwordOnline;
    }

    @Override
    public String toString() {
        numberCard = numberCard.substring(0,4) + '-' + numberCard.substring(4,8) + '-' + numberCard.substring(8,12) + '-' + numberCard.substring(12,16);
        return  numberCard + '\'' ;
    }
}
