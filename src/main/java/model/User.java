package model;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String phoneNumber;
    private String address;
    private String userName;
    private String passWord;
    private int role;

    public User(){

    }

    public User(String firstName, String lastName, String nationalCode, String phoneNumber, String address, String userName, String passWord, int role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public User(int id, String firstName, String lastName, String nationalCode, int role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.role = role;
    }

    public User(int id, int role, String firstName, String lastName, String nationalCode, String phoneNumber, String address, String userName, String passWord) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                '}';
    }
}
