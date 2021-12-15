package SharkCompanyFacade;

public class Card {
    
    private Employee employee;

    public Card(Employee employee){
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Card " + employee.getPerson() + ", Number: " + employee.getEmp_num();
    }
}
