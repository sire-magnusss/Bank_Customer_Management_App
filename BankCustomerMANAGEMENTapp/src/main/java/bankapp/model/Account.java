package bankapp.model;

import java.util.List;

public class Account {
    private int accountId;
    private int accountType;
    private double balance;
    private int customerId;
    private List<Transaction> transactions; // Add this field

    // Getters and Setters
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Transaction> getTransactions() { // Add getter
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) { // Add setter
        this.transactions = transactions;
    }
}
