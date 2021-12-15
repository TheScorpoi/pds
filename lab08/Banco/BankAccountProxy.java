package Banco;

public class BankAccountProxy implements BankAccount {

    private BankAccount bank;

    public BankAccountProxy(BankAccount bank) {
        this.bank = bank;
    }

    @Override
    public void deposit(double amount) {
        bank.deposit(amount);
    }

    @Override
    public boolean withdraw(double amount) {
        if (Company.user == User.OWNER) {
            return bank.withdraw(amount);
        } else {
            System.err.println("Access Denied - Can't withdraw money from employee");
            return false;
        }
    }

    @Override
    public double balance() {
        if (Company.user == User.OWNER) {
            return bank.balance();
        } else {
            System.err.println("Access Denied - Can't access balance of employee");
            return Double.NaN;
        }
    }

}
