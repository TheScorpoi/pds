package Cabazes;

public class Doce extends Produto{

    public Doce(String name, double weight) {
        super(name, weight);
    }

    @Override
    public String toString() {
        return "Doce " + super.toString();
    }
}
