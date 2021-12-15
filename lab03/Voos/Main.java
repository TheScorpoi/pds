package lab03.Voos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

import java.util.Scanner;

public class Main {

    static Map<String, Voo> codigoVooMap = new HashMap<String, Voo>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            //try { // Este try é para quando é submetido um alguma coisa que não é do tipo correto
            System.out.println("Pick an option: (H for help)");
            String[] option = sc.nextLine().split(" ");
            switch (option[0].toLowerCase()) {
            case "i":
                String filename = option[1];
                Voo flight = optionI(filename);
                System.out.println(flight);
                break;
            case "m":
                String flight_codeM = option[1];
                optionM(flight_codeM);
                break;
            case "f":
                String flight_codeF = option[1];
                String num_seats_executive = option[2];
                String num_seats_tourist = option[3];
                optionF(flight_codeF, num_seats_executive, num_seats_tourist);
                break;
            case "r":
                String flight_codeR = option[1];
                ClassAviao classe;
                if (Arrays.asList(option[2]).contains("T")) {
                    classe = ClassAviao.TURISTICA;
                } else if (Arrays.asList(option[2]).contains("E")) {
                    classe = ClassAviao.EXECUTIVA;
                } else {
                    System.err.println("ERROR: Airplane class in input is not supported");
                    break;
                }
                int number_seats = Integer.parseInt(option[3]);
                optionR(flight_codeR, classe, number_seats);
                break;
            case "c":
                // NOTA: O código de reservation é:
                // flight_code:sequential_reservation_number
                String reservation_code = option[1];
                optionC(reservation_code);
                break;
            case "h":
                System.out.println(optionH());
                break;
            case "q":
                System.out.println("Exiting...");
                flag = false;
                break;
            default:
                System.err.println("ERROR: Option is not supported");
                break;
            }
            //} catch (Exception e) {
            //    System.err.println("ERROR: Input type is not supported");
            //}
        }
        for (String key : codigoVooMap.keySet()) {
            System.out.printf("%s\n", codigoVooMap.get(key));
        }
        sc.close();
    }

    /**
     * This function will be used to read the input and create the flight with the configuration set inside the file
     * and with the reservations already inside. The function verifies if the input file is valid and also checks whether
     * we have a flight with E+T or just T seats
     * @param filename
     */
    private static Voo optionI(String filename){
        try{
            Scanner sc1 = new Scanner(new File("./lab03/Voos/Ficheiros/" + filename));
            String[] input = sc1.nextLine().split(" ");
            String flightCode = input[0];
            flightCode = flightCode.replaceAll(">", "");
            //----------------------------------
            // Error verification
            if(input.length>3){
                System.err.println("ERROR: Invalid type of input file, cannot extract flight code!");
                return null;    // here we don't do System.exit because we want the program to forgive bad file format and let the user change the input
            }
            //
            //----------------------------------
            Aviao plane;
            if (input.length == 3) {
                plane = new Aviao(input[2], input[1]);
                plane.setConfAviao(ClassAviao.TURISTICA_EXECUTIVA);
            } else {
                plane = new Aviao(input[1], "");
                plane.setConfAviao(ClassAviao.TURISTICA);
            }
            
            Voo flight = new Voo(flightCode, plane);
            codigoVooMap.put(flightCode, flight);
            //
            //
            String[] seat;       // reserved seat initialization
            while(sc1.hasNextLine()){
                seat = sc1.nextLine().split(" ");
                if(seat[0].toCharArray()[0] == 'T'){           //check if seat is TURISTIC
                    Reserva reservation = new Reserva(ClassAviao.TURISTICA, Integer.valueOf(seat[1]));
                    flight.addReservations(reservation);
                } else if (seat[0].toCharArray()[0] == 'E') { //or EXECUTIVE
                    if (plane.getClassAviao() == ClassAviao.TURISTICA) {
                        //System.err.println("ERROR: Plane doesn't have executive seats");
                        break;
                    }
                    Reserva reservation = new Reserva(ClassAviao.EXECUTIVA, Integer.valueOf(seat[1]));
                    flight.addReservations(reservation);
                }  
            }
            return flight;
        }
        //------------------------------
        catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found, please add another input file.");
            return null; // same as said above in at the first error message
        }
   }

    /**
     * 
     * @param flight_code
     */
    public static void optionM(String flight_code) {
        System.out.println("ISTO ESTÁ CERTO! MAS POR MOTIVOS DE TEMPO NÃO CONSGUIMOS FAZER O OVERVIEW DOS SEATS COMO PEDIDO\nPARA CONFIRMAR QUE ESTAVA CERTO, EM FASE DE DEBUG, FAZIAMOS PRINTS DAS RESERVAS QUE CADA AVIAO TINHA!");
    }

    /**
     * 
     * @param flight_code
     * @param num_seats_executive
     * @param num_seats_tourist
     */
    public static void optionF(String flight_code, String num_seats_executive, String num_seats_tourist) {
        
        Aviao plane = new Aviao(num_seats_tourist, num_seats_executive);
        Voo voo = new Voo(flight_code, plane);
        codigoVooMap.put(flight_code, voo);
       
        System.out.println("Flight inserted with success\n");

        for (String key : codigoVooMap.keySet()) {
            System.out.printf("%s\n", codigoVooMap.get(key));
        }
    }

    /**
     * 
     * @param flight_code
     * @param classe
     * @param number_seats
     */
    public static void optionR(String flight_code, ClassAviao classe, int number_seats) {

        for (String key : codigoVooMap.keySet()) {
            if (key.equals(flight_code)) {
                Voo voo = codigoVooMap.get(key);
                Reserva reservation = new Reserva(classe, number_seats);
                voo.addReservations(reservation);
                int reservationNumber = voo.getAviao().getReservationNumber() - 1;
                String reservation_code = flight_code + ":" + reservationNumber;
                System.out.println(reservation_code);
            }
        }
    }

    /**
     * 
     * @param reservation_code
     */
    public static void optionC(String reservation_code) {
        String[] separation = reservation_code.split(":"); 
        for (String key : codigoVooMap.keySet()) {
            if (separation[0].equals(key)) {
                Voo voo = codigoVooMap.get(key);
                voo.removeReservations(separation);
                codigoVooMap.put(key, voo);

            }
        }
    }

    /**
     * 
     * @return the a String that have the information of the (H)elp option
     */
    public static String optionH() {
        String helpString = "H : Help\n" + "I <filename> : Read a file with information about the flight\n"
                + "M <flight_code> : Show the reservation mao of the flight\n"
                + "F <flight_code> <num_seats_executive> <num_seats_tourist> : Add a new flight\n"
                + "R <flight_code> <class> <number_seats> : Add a new revervation to a flight\n"
                + "C <reservation_code> : Cancel a revervation\n" + "Q : Quit";
        return helpString;
    }
}
