package SharkCompanyFacade;


public class Employee {
    
    private Person person;
    private double salary;
    private int emp_num;
    private BankAccount bankAccount;

    public Employee(Person person, double salary) {
        this.person = person;
        this.salary = salary;
        bankAccount = new BankAccountProxy(new BankAccountImpl("PeDeMeia", 0));
    }

    public Person getPerson() {
        return person;
    }
    public void setEmpNum(int num){
        this.emp_num = num;
    }

    public int getEmp_num() {
        return emp_num;
    }

    public void setEmp_num(int emp_num) {
        this.emp_num = emp_num;
    }

    public double getSalary() {
        return salary;
    }

    public BankAccount getBankAccount() {
        //same as the before exercice, any doubt, go to class Person.java in Banco to 
        return new BankAccountProxy(bankAccount);
    }

    
}
