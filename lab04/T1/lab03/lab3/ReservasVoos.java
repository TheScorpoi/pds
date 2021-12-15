package lab3;

import java.io.File;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservasVoos {
	private static ArrayList<String> stuff = new ArrayList<>();
	private static ArrayList<String> flightCodes= new ArrayList<>();
	private static ArrayList<Integer> nTotalFilas= new ArrayList<>();
	private static ArrayList<Integer> nLugaresEmFila= new ArrayList<>();
	private static HashMap<String, ArrayList<String>> reservas = new HashMap<String, ArrayList<String>>();
	private static boolean close = false;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Escolha a sua opcao: (H para ajuda)\n");
		Scanner sc = new Scanner(System.in);
		while (true) {
		String txt = sc.nextLine();
		String[] splitting = txt.split(" ");
		getOption(splitting);
		//System.out.println("Here");
		//System.out.println(close);
		if (close) {
			break;
		}
		}
		System.out.println("Bye");
		sc.close();
	}
	
	public static void getOption(String[] splitting) {
		String opcao = splitting[0].toUpperCase(); // comando dado pelo user
		
			int NLugaresTuristica=0,NLugaresExecutiva=0, nRows=0, nSeatsPerRow=0; //nRows nunca usado
			int seatsTakenE = 0, seatsTakenT = 0;
			String[] header = null;
			switch (opcao) {
				case "I":
					
					File ficheiro = new File("./lab04/T1/lab03/lab3/" + splitting[1]);
					Scanner sc;
					try {
						sc = new Scanner(ficheiro);
						
						while (sc.hasNextLine()) {
					    	//System.out.println(sc.nextLine());
					    	stuff.add(sc.nextLine()); // vai ter as informa��es do ficheiro de voo
						}
						
						header = stuff.get(0).replace("x", " ").split(" ");//1� linha do txt separada
						String codVoo= header[0].replace(">", "");
						flightCodes.add(codVoo);
						System.out.println("C�digo de voo " + header[0].replace(">", "") ); 
						stuff.remove(0);
						reservas.put(codVoo, stuff);
						
						System.out.println(reservas.get(codVoo));
						for(int i = 0; i<reservas.get(codVoo).size(); i++) {
							System.out.println(reservas.get(codVoo).get(i));
						}
						
						if(header[3] != null) {
							NLugaresExecutiva = (int) Integer.parseInt(header[1]) * Integer.parseInt(header[2]); 
							NLugaresTuristica = (int) Integer.parseInt(header[3]) * Integer.parseInt(header[4]);
							
							nRows= (int) Integer.parseInt(header[1]) + Integer.parseInt(header[3]); 
							nSeatsPerRow=Integer.parseInt(header[2]);
							if(nSeatsPerRow < Integer.parseInt(header[4])) {
								nSeatsPerRow = Integer.parseInt(header[4]);
							}
							System.out.println("Lugares dispon�veis: "+ NLugaresExecutiva 
									+" lugares em classe Executiva; "+ NLugaresTuristica +" lugares em classe Tur�stica."); 
						}
						else {
							NLugaresTuristica = (int) Integer.parseInt(header[1]) * Integer.parseInt(header[2]);
							//nRows= (int) Integer.parseInt(header[1]); 
							nTotalFilas.add(Integer.parseInt(header[1]));
							nLugaresEmFila.add(Integer.parseInt(header[2]));
							System.out.println("Lugares dispon�veis: "+ NLugaresTuristica +" lugares em classe Tur�stica."
									+ "Classe executiva nao disponivel neste voo"); 
						}
						for(int i=1; i<stuff.size(); i++) {//obter n� de lugares reservados em cada classe
							String[] reservations = stuff.get(i).split(" ");
							//System.out.println(reservations[0].getClass());
							//System.out.println("E".getClass());
							
							if("E".equals(reservations[0])) {
								int seatsTaken = Integer.parseInt(reservations[1]);
								seatsTakenE += seatsTaken;
								//System.out.println(seatsTakenE);
								if(seatsTakenE > NLugaresExecutiva) {
									System.out.println("N�o foi poss�vel obter lugares para a reserva: " + stuff.get(i));
									break;
								}
							}
							if("T".equals(reservations[0])) {
								int seatsTaken = Integer.parseInt(reservations[1]);
								seatsTakenT += seatsTaken;
								//System.out.println(seatsTakenE);
								if(seatsTakenT > NLugaresTuristica) {
									System.out.println("N�o foi poss�vel obter lugares para a reserva: " + stuff.get(i));
									break;
								}
							}
							
						}
						
						
						
					} catch (FileNotFoundException e) {
						System.out.println("Fail");
						e.printStackTrace();
					}
					break;
				case "M":
					System.out.println("M");
					String flightCode = splitting[1];
					for(int i = 0; i < flightCodes.size(); i++) {
						if(flightCode == flightCodes.get(i)) {
							//char[][] seatDisposition = new char[nSeatsPerRow][nRows];	variável nunca usada
							
	
						}
					}
					break;
				case "F":
					System.out.println("F");
					String flightCode2 = splitting[1];
					String nExecSeats = splitting[2];
					String nTuristSeats;
					if(splitting[3] != null) {
						nTuristSeats= splitting[3];
						Voo Flight = new Voo(flightCode2, nExecSeats, nTuristSeats);
					}
					else {
						nTuristSeats = nExecSeats;
						Voo FlightRyanair= new Voo(flightCode2, nTuristSeats);
					}
				case "R":
					System.out.println("R");
					/** Variaveis nao usadas
					String flightCodeRes = splitting[1];
					String nExecSeats2 = splitting[2];
					String nTuristSeats2;
					*/
					//char[][] map = Voo.createSeatMap();
					break;
				case "C":
					System.out.println("C");
					break;
				case "Q":
					System.out.println("Q");
					close = true;
					break;
				case "H":
					System.out.println("I filename.txt -> Read a textfile which contains flight information\n ");
					System.out.println("M flight_code -> Show reservation map of a flight\n ");
					System.out.println("F flight_code num_seats_executive num_seats_tourist -> Add a new flight, with code, touristic seats and executive seats (optional)\n ");
					System.out.println("R flight_code class number_seats -> Add a new reservation for a flight poiting out the code, class and number of seats\n ");
					System.out.println("C reservation_code -> Cancel a reservation\n ");
					System.out.println("Q -> End program\n ");
	
			}
		
	}

}
