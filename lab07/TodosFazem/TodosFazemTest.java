package TodosFazem;

import java.util.Date;
//https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
//the Date class seems to be already deprecated, but we use it anyway

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

//Pattern used: Decorator

public class TodosFazemTest {
    
    public static void main(String[] args) {
        
        System.out.println("*****************************************\n***************TODOS*FAZEM***************\n*****************************************");

        EmployeeInterface emp1 = new Employee("Antonio Silva", 123);
        EmployeeInterface emp2 = new Employee("Henrique Granadeiro", 345);
        TeamMember tm1 = new TeamMember(new Employee("Ricardo Salgueiro", 121));
        TeamMember tm2 = new TeamMember(new Employee("Ana Maia", 87));
        TeamLeader tl1 = new TeamLeader(emp1);
        Manager m1 = new Manager(new TeamLeader(new TeamMember(emp2)));

        Date date = new Date();

        EmployeeInterface employeeList[] = {emp1, emp2, tm1, tm2, tl1, m1 };
        for (EmployeeInterface employee : employeeList) {
            employee.start(date);
            employee.work();
            employee.terminate(date);
        }
        
        System.out.println("*****************************************\n*****************************************\n*****************************************");
    }
}
