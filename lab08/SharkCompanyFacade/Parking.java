package SharkCompanyFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Parking {
    
    private List<Employee> parkingList = new ArrayList<>();

    public boolean allow(Employee employee, Company company) {
        if ( employee.getSalary() > company.getAvgSalary()) {
            System.out.println("Added to the parking list!\n");
            parkingList.add(employee);
            return true;
        }
        System.err.println("Employee can't get that parking spot!\n");
        return false;
    }

    public List<Employee> registered() {
        return Collections.unmodifiableList(parkingList);
    }

}
