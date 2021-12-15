package ChainOfResponsability;

public class PastaChef extends Chef {

    private int minutes =  10 + (int) (Math.random() * ((45 - 10) + 1));

    public void parse(String order) {
        if (candHandleOrder(order, "Pasta")) {
            System.out.println("PastaChef: Starting to cook " + order + ". Out in " + minutes + " minutes\n");
        } else {
            System.out.println("PastaChef: I can't cook that");
            super.parse(order);
        }
    }

}
