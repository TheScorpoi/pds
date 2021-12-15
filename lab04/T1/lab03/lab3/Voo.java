package lab3;

import java.util.HashMap;

public class Voo extends ReservasVoos{
	private static HashMap<String, String> reservas = new HashMap<String, String>();
	private String flight_code;
	private String num_seats_executive;
	private String num_seats_tourist;
	private int nRows;
	private int seatsPerRow;
	private int nExecutiveRows;
	private int ExecutiveRowsLen;
	private static char[][] seatMap;
	
	public Voo(String flight_code, String num_seats_executive, String num_seats_tourist) {
		this.flight_code = flight_code;
		this.num_seats_executive = num_seats_executive;
		this.num_seats_tourist = num_seats_tourist;
		String[] nums = num_seats_executive.split("x");
		String[] nums2 = num_seats_tourist.split("x");
		nExecutiveRows = Integer.parseInt(nums[0]);
		ExecutiveRowsLen =Integer.parseInt(nums[1]); 
		nRows = Integer.parseInt(nums[0]) + Integer.parseInt(nums2[0]);
		setnExecutiveRows(Integer.parseInt(nums[0]));
		seatsPerRow = nExecutiveRows;
		if(Integer.parseInt(nums2[1]) > Integer.parseInt(nums[1])){
			seatsPerRow = Integer.parseInt(nums2[1]);
		}
		setSeatMap(createSeatMap(nRows,seatsPerRow, nExecutiveRows, ExecutiveRowsLen));
	}
	public Voo(String flight_code, String num_seats_tourist) {
		this.flight_code = flight_code;
		this.num_seats_tourist = num_seats_tourist;
		String[] nums = num_seats_tourist.split("x");
		nRows = Integer.parseInt(nums[0]);
		seatsPerRow = Integer.parseInt(nums[1]);
	}

	public String getFlight_code() {
		return flight_code;
	}

	public void setFlight_code(String flight_code) {
		this.flight_code = flight_code;
	}

	public String getNum_seats_executive() {
		return num_seats_executive;
	}

	public void setNum_seats_executive(String num_seats_executive) {
		this.num_seats_executive = num_seats_executive;
	}

	public String getNum_seats_tourist() {
		return num_seats_tourist;
	}

	public void setNum_seats_tourist(String num_seats_tourist) {
		this.num_seats_tourist = num_seats_tourist;
	}
	
	public static void makeReservation(String flightcode, String classe, String lugares) {
		String reserva= String.join(" ", classe,lugares);
		String reservecode= String.join(flightcode, Integer.toString(reservas.size()+1));
		reservas.put(reservecode, reserva);
		//char[][] map = getSeatMap();
		System.out.println(reservecode);
	}
	public char[][] createSeatMap(int nRows,int seatsPerRow, int nExecutiveRows, int ExecutiveRowsLen){
		char[][]map= new char[seatsPerRow][nRows];
		for(int i=0; i<nRows; i++) {
			for(int j=0; j<seatsPerRow;j++) {
				map[i][j] = ' ';
			}
		}
		for(int i = 0; i<nExecutiveRows; i++) {
			for(int j=0; j<ExecutiveRowsLen;j++) {
				map[i][j] = '0';
			}
		}
		for(int i = nExecutiveRows-1; i<nRows; i++) {
			for(int j=0; j<seatsPerRow-ExecutiveRowsLen;j++) {
				map[i][j] = '0';
			}
		}
		return map;
	}
	public int getnExecutiveRows() {
		return nExecutiveRows;
	}
	public void setnExecutiveRows(int nExecutiveRows) {
		this.nExecutiveRows = nExecutiveRows;
	}
	public char[][] getSeatMap() {
		return seatMap;
	}
	public void setSeatMap(char[][] seatMap) {
		Voo.seatMap = seatMap;
	}
	
}
