package NullObject;

//!site used to construct this pattern:
//https://github.com/iluwatar/java-design-patterns/tree/master/null-object

public class NullEmployee extends Employee {

    private static final NullEmployee instance = new NullEmployee();
    
    private NullEmployee() {

    }

    public static NullEmployee getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "Name doesn't exists";
    }
    
    
}
