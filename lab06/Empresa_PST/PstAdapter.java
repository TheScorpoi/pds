
/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

public class PstAdapter implements PstInterface {

    private Database db;
    private Registos reg;

    public PstAdapter(Database db, Registos reg) {
        this.db = db;
        this.reg = reg;
    }

    @Override
    public void addEmployee(Employee e) {
        db.addEmployee(e);
    }

    @Override
    public void removeEmployee(int num) {
        reg.remove(num);
        db.deleteEmployee(num);
    }

    @Override
    public boolean isEmployee(int num) {

        for (Employee employee : db.getAllEmployees()) {
            if (employee.getEmp_num() == num) {
                return true;
            }
        }
        return reg.isEmpregado(num);
    }

    @Override
    public void listAllEmployee() {
        System.out.println("\nNOME       APELIDO      CODIGO     SALARIO");
        for (Empregado empregado : reg.listaDeEmpregados()) {
            System.out.println(empregado);
        }

        System.out.println("\nNAME                  EMP_NUM     SALARY");
        for (Employee employee : db.getAllEmployees()) {
            System.out.println(employee);
        }
    }

}
