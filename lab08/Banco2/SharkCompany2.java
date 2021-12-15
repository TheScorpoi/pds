package Banco2;

import java.util.List;

import Banco.User;

public class SharkCompany2 {
    
    public static void main(String[] args) {
        
        Person2[] persons = {
            new Person2("Joaquim Barbosa"),
            new Person2("Leonel Goncalves"),
            new Person2("Ze Augusto"),
            new Person2("Joana Silveira"),
            new Person2("Emma Silva"),
        };

        Company2 shark = new Company2();
        Company2.user = User.COMPANY;
        shark.admitPerson(persons[0], 1000);
        shark.admitPerson(persons[1], 1200);
        shark.admitPerson(persons[2], 1300);
        shark.admitPerson(persons[3], 1500);
        shark.admitPerson(persons[4], 1223);

        List<Employee2> sharkEmps = shark.employees();
        for (Employee2 e : sharkEmps) {
            System.out.println(e.getSalary());
        }
        shark.paySalaries(1);
        shark.paySalaries(1);
        for (Employee2 e : sharkEmps) {
            e.getBankAccount().withdraw(500);
            System.out.println(e.getBankAccount().balance());
        }
    }

}