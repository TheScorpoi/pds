package ChainOfResponsability;

public abstract class Chef {

    private Chef successor = null;

    public void parse(String order) {
        if (successor != null) {
            successor.parse(order);
        } else {
            System.err.println("We're sorry but that request can't be satisfied by our service!\n");
        }
    }

    protected boolean candHandleOrder(String order, String chef) {
        return (order == null) || order.contains(chef);
    }
    
    public Chef setSuccessor(Chef successor) {
        this.successor = successor;
        return this;
    }
}
