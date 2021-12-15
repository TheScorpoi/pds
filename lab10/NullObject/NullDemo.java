package NullObject;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */


public class NullDemo {
    
    public static void main(String[] args) {
        
        Employee emp1 = EmployeeFactory.getEmployee("Mac");
        Employee emp2 = EmployeeFactory.getEmployee("Janela");
        Employee emp3 = EmployeeFactory.getEmployee("Linux");
        Employee emp4 = EmployeeFactory.getEmployee("Mack");
        Employee emp5 = EmployeeFactory.getEmployee("Got");
        Employee emp6 = EmployeeFactory.getEmployee("Ubunto");
        Employee emp7 = EmployeeFactory.getEmployee("Ubunta");
        Employee emp8 = EmployeeFactory.getEmployee("Python");
        Employee emp9 = EmployeeFactory.getEmployee("Java");

        System.out.println(emp1.getName());
        System.out.println(emp2.getName());
        System.out.println(emp3.getName());
        System.out.println(emp4.getName());
        System.out.println(emp5.getName());
        System.out.println(emp6.getName());
        System.out.println(emp7.getName());
        System.out.println(emp8.getName());
        System.out.println(emp9.getName());

    }
}
