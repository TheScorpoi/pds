package Mediator;

public class Mediator implements MediatorInterface {

    private Runway runway;
    private Flight flight;
    public boolean runwayClear;

    //https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public Mediator() {
        System.out.println("\n------------CENTRAL CONTROL TOWER OF AVEIRO------------\n");
    }

    @Override
    public void registerRunway(Runway runway) {
        this.runway = runway;
        System.out.println(runway.getName() + ", with the ID " + runway.getId() + " and " +  runway.getLength() + " km");        
    }

    @Override
    public void registerFlight(Flight flight) {
        this.flight = flight;
        System.out.println(flight.getId() + " at: " + flight.getDate());        
        
    }

    @Override
    public boolean isRunwayClearToLand() {
        if (runwayClear) {
            System.out.println(ANSI_GREEN + ">> Landing permission granted!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + ">> Landing permission refused!" + ANSI_RESET);
        }
        return runwayClear;
    }

    @Override
    public boolean isRunwayClearToTakeOff() {
        if (runwayClear) {
            System.out.println(ANSI_GREEN + ">> Take off permission granted!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + ">> Take off permission refused!" + ANSI_RESET);
        }
        return runwayClear;
    }

    @Override
    public void setRunwayStatus(boolean status) {
        runwayClear = status;
    }

    //this following functions are here just because IDE 
    //is complaning with warnings, dont are important neither used 
    public Runway getRunway() {
        return runway;
    }

    public Flight getFlight() {
        return flight;
    }
}
