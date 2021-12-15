
/*
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

public class Main {

    public static void main(String[] args) {

        int[] luggage;
        Vehicle v;

        //Get vehicle for 1 passenger without luggage
        v = VehicleFactory.getVehicle(1);
        System.out.println(v);

        //Get vehicle for 1 passenger with two items of luggage
        luggage = new int[] { 100, 140 }; //two bags with a total volume of 240
        v = VehicleFactory.getVehicle(1, luggage);
        //System.out.println(v);

        //Get vehicle for 3 passengers with three items of luggage
        luggage = new int[] { 50, 200, 240 }; //three bags with a total volume of 490
        v = VehicleFactory.getVehicle(3, luggage);
        System.out.println(v);

        //Get vehicle for two passengers and wheelchair
        v = VehicleFactory.getVehicle(2, true);
        System.out.println(v);

        //Other examples...
        System.out.println();
        luggage = new int[] { 135, 72 };
        v = VehicleFactory.getVehicle(3, luggage);
        System.out.println(v);

        luggage = new int[] { 15, 44, 623 };
        v = VehicleFactory.getVehicle(2, luggage, false);
        System.out.println(v);
        luggage = new int[] { 15, 44, 888, 4, 13, 33};
        v = VehicleFactory.getVehicle(4, luggage, true);
        System.out.println(v);
        
    }
    
}
