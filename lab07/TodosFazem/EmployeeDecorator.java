package TodosFazem;

import java.util.Date;

public class EmployeeDecorator implements EmployeeInterface {

    protected EmployeeInterface employee;

    public EmployeeDecorator(EmployeeInterface employee){
        this.employee = employee;
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
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((employee == null) ? 0 : employee.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmployeeDecorator other = (EmployeeDecorator) obj;
        if (employee == null) {
            if (other.employee != null)
                return false;
        } else if (!employee.equals(other.employee))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EmployeeDecorator [employee=" + employee + "]";
    }

    
    
}
