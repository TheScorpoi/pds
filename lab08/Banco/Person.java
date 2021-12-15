package Banco;

public class Person {
    
    private String name;
    private BankAccount bankAccount;

    public Person(String n) {
        name = n;
        bankAccount = new BankAccountImpl("PeDeMeia", 0);
    }

    public String getName() {
        return name;
    }

    public BankAccount getBankAccount() {
        //before we have "return bankAccount", but then with the proxy implemented we need to change that return to this below
        //to garantee the user is a employee and not a company
        return new BankAccountProxy(bankAccount);
    }
}
