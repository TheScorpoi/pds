/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("-------------------ALINEA-1------------------\n");

        //-------------------------PETISCOS----------------------------
        System.out.println(
                "\n******************************\n*******P*E*T*I*S*C*O*S********\n******************************");

        Empregado emp1 = new Empregado("Humberto", "Delgado", 12, 1233.5);
        Empregado emp2 = new Empregado("Sofia", "Mota", 33, 1453.43);
        Empregado emp3 = new Empregado("Antonio", "Sala", 34, 567.89);
        Empregado emp4 = new Empregado("Sebastiao", "Ladeiras", 41, 1123.7);

        Registos registos = new Registos();

        registos.insere(emp1);
        registos.insere(emp2);
        registos.insere(emp3);
        registos.insere(emp4);

        System.out.println("\nInsercao dos empregados");
        printPetiscos(registos);

        registos.remove(34);
        System.out.println("\nRemocao do empregado com o codigo 34");
        printPetiscos(registos);

        System.out.printf("\nEmpregado numero 33 esta na empresa?");
        if (registos.isEmpregado(33) == true) {
            System.out.printf(" Sim\n");
        } else {
            System.out.printf(" Nao\n");
        }

        System.out.printf("\nEmpregado numero 34 esta na empresa?");
        if (registos.isEmpregado(34) == true) {
            System.out.printf(" Sim\n");
        } else {
            System.out.printf(" Nao\n");
        }

        //-------------------------SWEETS----------------------------
        System.out.println(
                "\n******************************\n*********S*W*E*E*T*S**********\n******************************");

        Employee empl1 = new Employee("Xavier Hernandez", 134, 3452.44);
        Employee empl2 = new Employee("Rith Smita", 234, 5552.12);
        Employee empl3 = new Employee("Lionel Messi", 67, 1252.23);
        Employee empl4 = new Employee("Sheldon Cooper", 222, 4432.28);
        Employee empl5 = new Employee("Michael Scott", 45, 5434.55);

        Database database = new Database();

        System.out.println("\nAdd the employees");
        database.addEmployee(empl1);
        database.addEmployee(empl2);
        database.addEmployee(empl3);
        database.addEmployee(empl4);
        database.addEmployee(empl5);

        printSweets(database);

        System.out.println("\nRemove a Employee");
        database.deleteEmployee(67);
        printSweets(database);

        System.out.println();

        System.out.println("-------------------ALINEA-2------------------\n");
        System.out.println(
                "\n******************************\n************P*S*T*************\n******************************");

        PstAdapter pstAdapter = new PstAdapter(database, registos);

        // Add employees
        System.out.println("ADD EMPLOYEE");

        Employee empl6 = new Employee("Helder Joao", 14, 3453.44);
        Employee empl7 = new Employee("Joaquim Barbosa", 765, 5522.12);
        Employee empl8 = new Employee("Manuel Henriques", 127, 1252.23);
        Employee empl9 = new Employee("Lucifer Cardoso", 232, 4332.28);
        Employee empl10 = new Employee("Maradona Cocaina", 77, 5534.55);

        pstAdapter.addEmployee(empl6);
        pstAdapter.addEmployee(empl7);
        pstAdapter.addEmployee(empl8);
        pstAdapter.addEmployee(empl9);
        pstAdapter.addEmployee(empl10);

        pstAdapter.listAllEmployee();

        //Remove a employee
        System.out.println("\nREMOVE EMPLOYEE");

        pstAdapter.removeEmployee(45);
        pstAdapter.removeEmployee(12);
        pstAdapter.removeEmployee(134);

        pstAdapter.listAllEmployee();

        //Verify if a employee exist in a company
        System.out.printf("\nEmployee with number 234 belongs to a company? ");
        if (pstAdapter.isEmployee(234) == true) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        System.out.printf("Employee with number 45 belongs to a company? ");
        if (pstAdapter.isEmployee(45) == true) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        System.out.printf("Employee with number 33 belongs to a company? ");
        if (pstAdapter.isEmployee(33) == true) {
            System.out.println("Yes\n");
        } else {
            System.out.println("No\n");
        }
    }
    

    public static void printPetiscos(Registos registos) {
        System.out.println("\nNOME       APELIDO      CODIGO     SALARIO");
        for (Empregado empregado : registos.listaDeEmpregados()) {
            System.out.println(empregado);
        }
    }

    public static void printSweets(Database database) {
        System.out.println("\nNAME                  EMP_NUM     SALARY");
        for (Employee employee : database.getAllEmployees()) {
            System.out.println(employee);
        }
    }

}