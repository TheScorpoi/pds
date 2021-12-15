package Lei_Lao;

public class Gestor extends Observer {

    private String name;
    private int productKey;

    public Gestor(String name) {
        this.name = name;
        this.productKey=0;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void sellItem(Produto produto, String name, Double value){
        
        System.out.println("Client " + name+" has the highest bid ("+value+") for the item "+produto.getUnique_key()+"!\nThe item will be sold if there are no higher bids...");
        for (int i = 1; i < 4; i++) {
            System.out.println("Goes "+i);
            wait(1000);
        }
        System.out.println("Item sold to "+ name);
    }
    
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void update(double value, Produto produto, String nome) {
        System.out.println("-------------------------------------");
        System.out.println(nome + " NEW BID TO PRODUCT "+ produto.getUnique_key() +": " + value);
        System.out.println("-------------------------------------");
    }
    
    public void start(Produto produto) {
        produto.setKey(++this.productKey);
        produto.start();
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
