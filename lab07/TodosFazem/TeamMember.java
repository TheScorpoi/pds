package TodosFazem;

import java.util.Date;

public class TeamMember extends EmployeeDecorator {

    public TeamMember(EmployeeInterface employee) {
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
        System.out.print("É membro da equipa");
        colaborate();
    }

    public void colaborate() {
        System.out.println(", e está a colaborar");
        
    }
}
