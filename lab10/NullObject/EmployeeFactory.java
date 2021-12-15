package NullObject;

public class EmployeeFactory {

    public static final String[] names = { "Mac", "Linux", "Win", "Ubuntu", "Git", "Java" };

    public static Employee getEmployee(String name) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(name)) {
                return new Programmer(name);
            }
        }
        return NullEmployee.getInstance();
    }
}
