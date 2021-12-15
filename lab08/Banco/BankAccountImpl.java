package Banco;

public class BankAccountImpl implements BankAccount {

    private String bank;
    private double balance;

    public BankAccountImpl(String bank, double initialDeposit) {
        this.bank = bank;
        balance = initialDeposit;
    }

    public String getBank() {
        return bank;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.err.println("You don't have enough money to withdraw");
            return false;
        }
        balance -= amount;
        return true;
    }

    @Override
    public double balance() {
        return balance;
    }
}
