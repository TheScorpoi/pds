package NullObject;

public class Programmer extends Employee{

    public Programmer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    
}
