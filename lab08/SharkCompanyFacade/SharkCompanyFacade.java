package SharkCompanyFacade;

import java.util.List;

//import java.util.List;
/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */
// Pattern Used: Facade and Proxy(the same as the previous exercice)

public class SharkCompanyFacade {

    public static void main(String[] args) {
    
        CompanyFacade facade = new CompanyFacade();
        Company shark = facade.getCompany();
        Company.user = User.COMPANY;
        
        Person[] persons = {
            new Person("Joaquim Barbosa"),
            new Person("Leonel Goncalves"), new Person("Ze Augusto"),
            new Person("Joana Silveira"), 
            new Person("Emma Silva") 
        };

        facade.admitEmployee(persons[0], 1000.0);
        facade.admitEmployee(persons[1], 1200.0);
        facade.admitEmployee(persons[2], 1300.0);
        facade.admitEmployee(persons[3], 1500.0);
        facade.admitEmployee(persons[4], 1223.0);
        
        List<Employee> sharkEmps = shark.employees();

        //Tests to see permissions of balance
        String permission;
        if (Company.user == User.COMPANY) {
            permission = "(COMPANY) access must be denied";
        } else {
            permission = "(OWNER) access must be grants";
        }
        System.out.println("Seeing Balance of Bank accounts... " + permission);
        for (Employee e : sharkEmps) {
            System.out.println(e.getBankAccount().balance());
        }
        
        //Salaries
        System.out.println("\nShark Company pay salaries of january");
        shark.paySalaries(1);
        System.out.println("Shark Company pay salaries of february\n");
        shark.paySalaries(2);

        //Tests to see permissons of withdraw and balance
        if (Company.user == User.COMPANY) {
            permission = "(COMPANY) cannot see balance and withdraw from employee bank account";
        } else {
            permission = "(OWNER) can withdraw money and see balance";
        }
        System.out.println("Seeing if can withdraw and after see balance... " + permission + "\n");
        for (Employee e : sharkEmps) {
            e.getBankAccount().withdraw(500);
            System.out.println(e.getBankAccount().balance());
        }
    }
}
