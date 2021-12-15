package SerializacaoJSON;

public class Ship {
    
    private String name;
    private int size;
    private String[] passageiros;
    private Owner owner;
 
    public Ship (String name, int size) {
        super();
        this.name = name;
        this.size = size;
    }

    public String getName(){
        return name;
    }

    public int getSize(){
        return size;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner){
        this.owner = owner;
    }

    public String[] getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(String[] passageiros) {
        this.passageiros = passageiros;
    }

}
