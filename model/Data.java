package model;

public class Data {
    String name;
    String email;
    String account_number;
    String security_pin;
    float balance;

    public Data(String email, String account_number) {
        this.email = email;
        this.account_number = account_number;
    }

    public Data(String email, String account_number, String security_pin) {
        this.email = email;
        this.account_number = account_number;
        this.security_pin = security_pin;
    }

    public Data(String name, String email, String account_number, String security_pin, float balance) {
        this.name = name;
        this.email = email;
        this.account_number = account_number;
        this.balance = balance;
        this.security_pin = security_pin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setSecurity_pin(String security_pin) {
        this.security_pin = security_pin;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAccount_number() {
        return account_number;
    }

    public float getBalance() {
        return balance;
    }

    public String getSecurity_pin() {
        return security_pin;
    }
}
