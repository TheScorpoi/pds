package SharkCompanyFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SocialSecurity {
    
    private List<Employee> socialSecurityList = new ArrayList<>();

    public void regist(Employee employee) {
        socialSecurityList.add(employee);
        System.out.println("Registered new employee in social security!");
    }

    public List<Employee> registered() {
        return Collections.unmodifiableList(socialSecurityList);
    }

}
