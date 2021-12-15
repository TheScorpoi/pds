
/*
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

public class VehicleFactory {

    /**
     * 
     * @param i numbers of passengers
     * @param luggage array with the volume of each item
     * @return a Vehicle that response to the necessities given  
     */
    public static Vehicle getVehicle(int i, int[] luggage) {
        int volumeTotal = 0;
        
        for (int j : luggage) {
            volumeTotal += j;
        }

        if (i == 1 && volumeTotal <= 250) {
            return new Micro(i, luggage.length);
        } else if (i <= 3 && volumeTotal <= 250) {
            return new City(i, luggage.length);
        } else if (i <= 4 && volumeTotal <= 600) {
            return new Family(i, luggage.length);
        } else if (i <= 4 && volumeTotal <= 1000) {
            return new Van(i, luggage.length);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /** 
     * 
     * @param i numbers of passengers
     * @return a Vehicle that response to the necessities given 
     */
    public static Vehicle getVehicle(int i) {
        if (i == 1) {
            return new Scooter(i);
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * 
     * @param i numbers of passengers
     * @param op boolean to say if the Vehicle have the wheelchair or not
     * @return a Vehicle that response to the necessities given 
     */
    public static Vehicle getVehicle(int i, boolean op) {
        if (i <= 4) {
            return new Van(i, op);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 
     * @param i numbers of passengers
     * @param luggage array with the volume of each item
     * @param op boolean to say if the Vehicle have the wheelchair or not
     * @return a Vehicle that response to the necessities given 
     */
    public static Vehicle getVehicle(int i, int[] luggage, boolean op) {
        int volumeTotal = 0;
        for (int j : luggage) {
            volumeTotal += j;
        }
        if (i <= 4 && volumeTotal <= 1000) {
            return new Van(i, luggage.length,op);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
