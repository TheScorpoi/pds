package TodosFazem;

import java.util.Date;

public class TeamLeader extends EmployeeDecorator {

    public TeamLeader(EmployeeInterface employee) {
        super(employee);
    }

    @Override
    public void start(Date date) {
        employee.start(date);        
    }
    
    @Override
    public void terminate(Date date) {
        employee.terminate(date);

    }
    @Override
    public void work() {
        employee.work();
        System.out.print("É o lider da equipa");
        plan();
    }
    
    public void plan() {
        System.out.println(", e está a planear");
        
    }
    
}
