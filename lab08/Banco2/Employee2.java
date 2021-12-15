package Banco2;

//re-use the code from the topic before 
import Banco.BankAccount;
import Banco.BankAccountImpl;
import Banco.BankAccountProxy;

public class Employee2 {
    
    private Person2 person;
    private double salary;
    private BankAccount bankAccount;

    public Employee2(Person2 person, double salary) {
        this.person = person;
        this.salary = salary;
        bankAccount = new BankAccountProxy(new BankAccountImpl("PeDeMeia", 0));
    }

    public Person2 getPerson() {
        return person;
    }

    public double getSalary() {
        return salary;
    }

    public BankAccount getBankAccount() {
        //same as the before exercice, any doubt, go to class Person.java in Banco to 
        return new BankAccountProxy(bankAccount);
    }

}
