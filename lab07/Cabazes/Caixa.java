package Cabazes;

import java.util.ArrayList;
import java.util.List;

public class Caixa extends Produto {

    private List<Produto> produtosList = new ArrayList<>();
    private int totalWeight = 0;
    static StringBuilder sb = new StringBuilder();

    public Caixa(String name, double weight) {
        super(name, weight);
    }

    public void add(Produto p) {
        this.produtosList.add(p);
        this.totalWeight += p.getWeight();
    }

    public void draw() {
        for (Produto produto : produtosList) {
            if( produto instanceof Caixa){
                this.totalWeight += produto.getTotalWeight();
            }
        }
        this.totalWeight+=this.getWeight();

        System.out.println(sb.toString() + "* " + this.toString());
        sb.append("    ");
        for (Produto produto : produtosList) {

            if (produto instanceof Caixa) {
                produto.draw();
            } else {
                System.out.println(sb.toString() + produto);
            }
        }
        sb.setLength(sb.length() - 4);
    }
    @Override
    public int getTotalWeight(){
        return this.totalWeight;
    }

    @Override
    public String toString() {
        return "Caixa " + super.toString() + " ; Total: " + this.totalWeight;
    }
}
