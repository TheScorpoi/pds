package Mediator;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

//!DISCLAIMER
// We don't use threads (we should but many project now and don't have time) 
// to simulate more than one airplane (landing or taking off at the same time)
// so, with hardcoded we simalate the runway being in use by the control tower
// that sets the runwayClear to false, and then planes can't land or take off

public class MediatorDemo {
    //https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";    
    public static void main(String[] args) {

        MediatorInterface controlTower = new Mediator();

        Runway runway1 = new Runway(controlTower, "North-Runway", "16",4.134);
        Runway runway2 = new Runway(controlTower, "West-Runway", "27",5.256);
        
        System.out.println("List of runways in use:");
        controlTower.registerRunway(runway1);
        controlTower.registerRunway(runway2);

        //the date in flight are just to add some information on it, because it never is really used
        Flight flight1 = new Flight(controlTower, "EAS3566", "23/06/2021 09:32");
        Flight flight2 = new Flight(controlTower, "RAY2312", "23/06/2021 10:15");
        Flight flight3 = new Flight(controlTower, "TAP2121", "23/06/2021 12:33");
        Flight flight4 = new Flight(controlTower, "LUF1231", "23/06/2021 15:56");
        Flight flight5 = new Flight(controlTower, "EMI1252", "23/06/2021 10:12");

        System.out.println("\nList of flights that the control tower will manage:");
        controlTower.registerFlight(flight1);
        controlTower.registerFlight(flight2);
        controlTower.registerFlight(flight3);
        controlTower.registerFlight(flight4);
        controlTower.registerFlight(flight5);
        System.out.println("\n");

        flight1.land();
        flight5.land();

        flight1.takeOff();

        controlTower.setRunwayStatus(false);
        System.out.println(ANSI_YELLOW + ">> AIRPLANE CRASHED ON RUNWAY! FIRE EVERYWHERE!" + ANSI_RESET);

        flight3.land();
        flight2.takeOff();
        flight3.takeOff();

        controlTower.setRunwayStatus(true);
        System.out.println(ANSI_CYAN + ">> RUNWAY IS CLEAR AGAIN! AIRPLANES CAN LAND AND TAKE OFF!" + ANSI_RESET);

        flight4.takeOff();

        flight5.takeOff();
        flight2.land();

        flight4.land();
    }
}
