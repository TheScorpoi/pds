package SharkCompanyFacade;

import java.util.ArrayList;
import java.util.List;

public class CompanyFacade extends Company {
    private int empNum = 1;

    private Company company = new Company();
    private SocialSecurity socialSecurity = new SocialSecurity();
    private Insurance insurance = new Insurance();
    private List<Card> card = new ArrayList<Card>();
    private Parking parking = new Parking();

    public void admitEmployee(Person p, double salary) {
        Employee employee = new Employee(p, salary);
        employee.setEmpNum(this.empNum);
        this.empNum++;
        company.admitEmployee(p, salary);
        Card cartao = new Card(employee);
        card.add(cartao);
        System.out.println("New " + cartao.toString());
        socialSecurity.regist(employee);
        insurance.regist(employee);
        parking.allow(employee, this.company);

    }

    public Company getCompany() {
        return company;
    }

}
