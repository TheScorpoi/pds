package ChainOfResponsability;

public class SushiChef extends Chef {
    
    private int minutes = 10 + (int) (Math.random() * ((45 - 10) + 1));

    public void parse(String order) {
        if (candHandleOrder(order, "sushi")) {
            System.out.println("SushiChef: Starting to cook " + order + ". Out in " + minutes + " minutes\n");
        } else {
            System.out.println("SushiChef: I can't cook that");
            super.parse(order);
        }
    }

}
