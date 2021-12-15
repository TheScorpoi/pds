package Banco;

import java.util.List;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */
//Pattern Used: Proxy

public class SharkCompany {

    public static void main(String[] args) {

        Company shark = new Company();
        Company.user = User.COMPANY;
        shark.admitPerson("Maria Silva", 1000);
        shark.admitPerson("Manuel Pereira", 900);
        shark.admitPerson("Aurora Machado", 1200);
        shark.admitPerson("Augusto Lima", 1100);
        List<Employee> sharkEmps = shark.employees();
        for (Employee e : sharkEmps) {
            System.out.println(e.getBankAccount().balance());

        }
        shark.paySalaries(1);
        shark.paySalaries(1);
        shark.paySalaries(1);
        for (Employee e : sharkEmps) {
            e.getBankAccount().withdraw(500);
            System.out.println(e.getBankAccount().balance());
        }
    }
}
