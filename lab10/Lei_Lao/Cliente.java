package Lei_Lao;

import java.util.ArrayList;
import java.util.HashMap;

public class Cliente extends Observer {

    private String name;
    private HashMap<Produto, ArrayList<Double>> produtoLst = new HashMap<Produto, ArrayList<Double>>();
    

    public Cliente(String name) {
        this.name = name;
    }

    public boolean makeAnOffer(Produto produto, double value) {
        if(!this.produtoLst.containsKey(produto)){
            this.produtoLst.put(produto, new ArrayList<Double>());
            this.produtoLst.get(produto).add(value);
        }
        return produto.registerBidding(this, value);
    }

    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void update(double value, Produto produto, String nome) {
        if(this.produtoLst.containsKey(produto)){
            this.produtoLst.get(produto).add(value);
        }
        System.out.println(name + " made an offer of "+value+" for product no "+ produto.getUnique_key());   
    }
}
