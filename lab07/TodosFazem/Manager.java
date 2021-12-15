package TodosFazem;

import java.util.Date;

public class Manager extends EmployeeDecorator{

    public Manager(EmployeeInterface employee) {
        super(employee);
    }

    //? ask the teacher if this is worth, bacause are the same functions thar are in Decorator (here, leader, and member as well)
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
        System.out.print("É o manager");
        manage();
    }

    public void manage() {
        System.out.println(", e está a fazer o que um manager faz");
    }
}
