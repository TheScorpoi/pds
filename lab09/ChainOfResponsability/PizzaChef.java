package ChainOfResponsability;

public class PizzaChef extends Chef {

    private int minutes =  10 + (int) (Math.random() * ((45 - 10) + 1));

    public void parse(String order) {
        if (candHandleOrder(order, "pizza")) {
            System.out.println("PizzaChef: Starting to cook " + order + ". Out in " + minutes + " minutes\n");
        } else {
            System.out.println("PizzaChef: I can't cook that");
            super.parse(order);
        }
    }
}
