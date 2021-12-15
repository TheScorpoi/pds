package Mediator;

public class Flight implements Option {

    private MediatorInterface mediator;
    private String id;
    private String date;

    public Flight(MediatorInterface mediator, String id, String date) {
        this.mediator = mediator;
        this.id = id;
        this.date = date;
    }

    public String getId(){
        return id;
    }

    public String getDate() {
        return date;
    }

    @Override
    public void land() {
        System.out.println("<< " + this.id + ", want to land");
        if (mediator.isRunwayClearToLand()){
            System.out.println(">> Runway is clear, you could land!");
            mediator.setRunwayStatus(false);
            wait(1000);
            System.out.println("<< " + this.id + " confirms that have a successful landing\n\n");
            mediator.setRunwayStatus(true);
            wait(1000);
        } else {
            System.out.println(">> Runway is in use! You have to wait!\n\n");
            wait(1000);
        }
    }

    @Override
    public void takeOff() {
        System.out.println("<< " + this.id + ", want to take off");
        if (mediator.isRunwayClearToTakeOff()){
            System.out.println(">> Runway is clear, you could takeOff!");
            mediator.setRunwayStatus(false);
            wait(1000);
            System.out.println("<< " + this.id + " confirms that have a successful take off\n\n");
            mediator.setRunwayStatus(true);
            wait(1000);
        } else {
            System.out.println(">> Runway is in use! You have to wait!\n\n");
            wait(1000);
        }
    }
    
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
