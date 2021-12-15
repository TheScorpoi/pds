package Mediator;

public class Runway implements Option {

    private MediatorInterface mediator;
    private String name;
    private String id;
    private double length;

    public Runway(MediatorInterface mediator, String name, String id, double length) {
        this.mediator = mediator;
        this.name = name;
        this.id = id;
        this.length = length;
        //by default, when a runway is created it is clear
        mediator.setRunwayStatus(true);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getLength() {
        return length;
    }
    @Override
    public void land() {
        System.out.println(">> Landing permission granted!");
        mediator.setRunwayStatus(true);
    }

    @Override
    public void takeOff() {
        System.out.println(">> Take off permission granted!");
        mediator.setRunwayStatus(true);   
    }
}
