package Cabazes;

public class Bedida extends Produto {

    public Bedida(String name, double weight) {
        super(name, weight);
    }

    @Override
    public String toString() {
        return "Bedida " + super.toString();
    }   
    
}
