
/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

import java.util.Vector;

//Sweets
public class Database {

    private Vector<Employee> employees;

    public Database() {
        employees = new Vector<>();
    }

    public boolean addEmployee(Employee employee) {

        if (employees.contains(employee)) {
            System.out.println("Employee is already in the list");
            return false;
        } else {
            employees.add(employee);
            return true;
        }
    }

    public void deleteEmployee(long emp_num) {

        for (Employee employee : employees) {
            if (emp_num == employee.getEmp_num()) {
                employees.remove(employee);
                break;
            }
        }

    }

    public Employee[] getAllEmployees() {

        return employees.toArray(new Employee[0]);
    }

}
