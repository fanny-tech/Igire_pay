package Lab1;

import java.time.LocalDateTime;

public class Account {
        private String accountNumber;
        private double balance;

        // Constructor
        public Account(String accountNumber, double balance) {
            this.accountNumber = accountNumber;
            this.balance = balance;
        }

        public Account() {

        }

        // Getters na Setters
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

        // (Deposit)
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println(" deposit: " + amount + " successfull.");
            }
        }

         //withdraw
        public void withdraw(double amount) {
            if (amount > 0 && balance >= amount) {
                balance -= amount;
                System.out.println("withdraw: " + amount);
            } else {
                System.out.println(" Error: invalid!");
            }
        }

        @Override
        public String toString() {
            return "Account [Account Number=" + accountNumber + ", Balance=" + balance + "]";
        }



    }

