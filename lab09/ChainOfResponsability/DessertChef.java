package ChainOfResponsability;

public class DessertChef extends Chef {

    private int minutes =  10 + (int) (Math.random() * ((45 - 10) + 1));

    public void parse(String order) {
        if (candHandleOrder(order, "dessert")) {
            System.out.println("DessertChef: Starting to cook " + order + ". Out in " + minutes + " minutes\n");
        } else {
            System.out.println("DessertChef: I can't cook that");
            super.parse(order);
        }
    }

}
