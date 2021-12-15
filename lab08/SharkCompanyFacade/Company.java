package SharkCompanyFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Company {

    public static User user;
    private List<Employee> emps = new ArrayList<>();

    public void admitEmployee(Person person, double salary) {
        Employee e = new Employee(person, salary);
        emps.add(e);

    }

    public void paySalaries(int month) {
        for (Employee e : emps) {
            BankAccount ba = e.getBankAccount();
            ba.deposit(e.getSalary());
        }
    }

    public double getAvgSalary() {
        int n_employees = 0;
        double total_salaries = 0;

        for (Employee employee : emps) {
            total_salaries += employee.getSalary();
            n_employees++;
        }

        return total_salaries / n_employees;
    }

    public List<Employee> employees() {
        return Collections.unmodifiableList(emps);
    }
}
