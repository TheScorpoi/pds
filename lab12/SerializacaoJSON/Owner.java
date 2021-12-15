package SerializacaoJSON;

public class Owner {
    
    private String name;

    public Owner (String name) {
        this.name =  name;
    }

    @Override
    public String toString() {
        return "Name: " + name ;
    }
}
