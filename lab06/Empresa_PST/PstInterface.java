/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

public interface PstInterface {
    /**
     * Add a employee of Employee class
     * 
     * @param e object of Employee class
     */
    public void addEmployee(Employee e);

    /**
     * Generic function to remove a employee, doesn't matter which class 
     * 
     * @param num number of employee to remove
     */
    public void removeEmployee(int num);

    /**
     * Generic function to verify if a employee belongs to a company
     * 
     * @param num number of employee to search
     * @return true -> employee belongs to a company
     * @return false -> employee doesn't belong to a company
     */
    public boolean isEmployee(int num);

    /**
     * Print a list of employee for each company
     */
    public void listAllEmployee();
}
