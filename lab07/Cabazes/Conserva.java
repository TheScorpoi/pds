package Cabazes;

public class Conserva extends Produto {

    public Conserva(String name, double weight) {
        super(name, weight);
    }

    @Override
    public String toString() {
        return "Conserva " + super.toString();
    }
}
