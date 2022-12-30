package com.driver;

public class Main {
    public static void main(String[] args) throws Exception {
        CurrentAccount c=new CurrentAccount("Amal",18000,"122");
        c.getBalance();
        c.validateLicenseId();
    }
}