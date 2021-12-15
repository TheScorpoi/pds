package SharkCompanyFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Insurance {
    
    private List<Employee> insuranceList = new ArrayList<>();

    public void regist(Employee employee) {
        insuranceList.add(employee);
        System.out.println("Registered new employee in insurance!");
    }

    public List<Employee> registered() {
        return Collections.unmodifiableList(insuranceList);
    }
}
