/*
Date : 2018.08.28
Maker : LT
Description : Study extends
 */

package com.company;

public class _002_BankManager {
    public static void main() {
        CheckingAccount ca = new CheckingAccount();
        SavingAccount sa = new SavingAccount();
        CertificateOfDeposit cod = new CertificateOfDeposit();

        ca.setAccount("CA001");
        sa.setBalance("128.00");
        cod.setAccount("COD1");

        System.out.println("ca.account  : " + ca.getAccount());
        System.out.println("sa.balance  : " + sa.getBalance());
        System.out.println("cod.account : " + cod.getAccount());

    }
}

class CheckingAccount extends BankAccount {
    private String task1;
}

class SavingAccount extends BankAccount {
    private String task2;
}

class CertificateOfDeposit extends BankAccount {
    private String task3;
}

class BankAccount {
    private String account;
    private String balance;

    String getAccount() {
        return account;
    }
    String getBalance() {
        return balance;
    }

    void setAccount(String account) {
        this.account = account;
    }
    void setBalance(String balance) {
        this.balance = balance;
    }
}