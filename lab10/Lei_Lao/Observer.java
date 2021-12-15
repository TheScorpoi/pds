package Lei_Lao;

public abstract class Observer {
    
    protected Produto produto;

    public abstract void update(double value, Produto produto, String name);

    public abstract String getName();
}
