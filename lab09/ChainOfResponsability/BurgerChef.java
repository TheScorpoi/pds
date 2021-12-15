package ChainOfResponsability;

public class BurgerChef extends Chef {

    private int minutes =  10 + (int) (Math.random() * ((45 - 10) + 1));

    public void parse(String order) {
        if (candHandleOrder(order, "burger")) {
            System.out.println("BurgerChef: Starting to cook " + order + ". Out in " + minutes + " minutes\n");
        } else {
            System.out.println("BurgerChef: I can't cook that");
            super.parse(order);
        }
    }
}
