package lab03.Voos;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

public class Aviao {

    private ClassAviao classAviao;
    private int[] seatsT;
    private int[] seatsE; // seatsE[n] = "empty";
    private int rowsT;
    private int rowsE;
    private int nChairsE;
    private int nChairsT;
    private int reservationNumber = 1;
    private int sizeT;
    private int sizeE;
    private int availableSeatsT;
    private int availableSeatsE;

    public Map<Integer, Character> seats = new HashMap<Integer, Character>();

    public Aviao(String confT, String confE) {
        String[] arrayT = confT.split("x");
        String[] arrayE = confE.split("x");
        this.rowsT = Integer.parseInt(String.valueOf(arrayT[0]));
        this.nChairsT = Integer.parseInt(String.valueOf(arrayT[1]));
        this.sizeT = this.nChairsT * this.rowsT;
        this.seatsT = new int[sizeT];
        this.availableSeatsT = sizeT;
        if (confE != "") {
            this.classAviao = ClassAviao.TURISTICA_EXECUTIVA;
            this.rowsE = Integer.parseInt(String.valueOf(arrayE[0]));
            this.nChairsE = Integer.parseInt(String.valueOf(arrayE[1]));
            this.sizeE = this.nChairsE * this.rowsE;
            this.availableSeatsE = sizeE;
            this.seatsE = new int[sizeE]; 

        } else {
            this.rowsE = 0;
            this.nChairsE = 0;
            this.sizeE = this.nChairsE * this.rowsE;
            this.seatsE = null;
        }

    }

    public int[] getSeatsT() {
        return seatsT;
    }

    public void setSeatsT(int[] seatsT) {
        this.seatsT = seatsT;
    }

    public int[] getSeatsE() {
        return seatsE;
    }

    public void setSeatsE(int[] seatsE) {
        this.seatsE = seatsE;
    }

    public int getRowsT() {
        return rowsT;
    }

    public void setRowsT(int rowsT) {
        this.rowsT = rowsT;
    }

    public int getRowsE() {
        return rowsE;
    }

    public void setRowsE(int rowsE) {
        this.rowsE = rowsE;
    }

    public int getnChairsE() {
        return nChairsE;
    }

    public void setnChairsE(int nChairsE) {
        this.nChairsE = nChairsE;
    }

    public int getnChairsT() {
        return nChairsT;
    }

    public void setnChairsT(int nChairsT) {
        this.nChairsT = nChairsT;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public int getSizeT() {
        return sizeT;
    }

    public void setSizeT(int sizeT) {
        this.sizeT = sizeT;
    }

    public int getSizeE() {
        return sizeE;
    }

    public void setSizeE(int sizeE) {
        this.sizeE = sizeE;
    }

    public Map<Integer, Character> getSeats() {
        return seats;
    }

    public void setSeats(Map<Integer, Character> seats) {
        this.seats = seats;
    }

    public ClassAviao getClassAviao() {
        return this.classAviao;
    }

    public void setClassAviao(ClassAviao classAviao) {
        this.classAviao = classAviao;
    }
    
    public void setConfAviao(ClassAviao classe) {
        this.classAviao = classe;
    }

    public int getAvailableSeatsT() {
        return availableSeatsT;
    }

    public void setAvailableSeatsT(int availableSeatsT) {
        this.availableSeatsT = availableSeatsT;
    }

    public int getAvailableSeatsE() {
        return availableSeatsE;
    }

    public void setAvailableSeatsE(int availableSeatsE) {
        this.availableSeatsE = availableSeatsE;
    }

    public void makeReservation(Reserva reservation) {
        int freeInRow = 0;
        int seatToStart = 0;
        if (reservation.getClassName() == ClassAviao.TURISTICA) {
            if(reservation.getnPassageiros()>=sizeT){
                System.out.println("ERROR: Not enough seats in plane to make reservation");
                return;
            }
            if(availableSeatsT==0){
                System.out.println("ERROR:No available seats to make reservation.");
                return;
            }
            if(reservation.getnPassageiros()> this.availableSeatsT){
                System.out.println("ERROR: Not enough seats available for Turistic reservation!");
                return;
            }
            
            for (int i = 0; i < sizeT; i++) {
                freeInRow = 0;
                if (seatsT[i] == 0 && i<sizeT-reservation.getnPassageiros()) {
                    for (int j = 0; j < reservation.getnPassageiros(); j++){
                        if(seatsT[i+j]==0){
                            freeInRow ++;
                        }else{
                            freeInRow=-1;
                        }
                    }//endfor

                    if(freeInRow==reservation.getnPassageiros()-1){
                        seatToStart=i;
                        break;
                    } else if (freeInRow == -1){
                        i+=(reservation.getnPassageiros()-1);
                    }
                }//endif
            }//endfor
            if (freeInRow == -1 && this.availableSeatsT != 0) {
                int j = 0;
                for (int i = 0; i < sizeT; i++) {
                    if (seatsT[i] == 0 && j < reservation.getnPassageiros()) {
                        seatsT[i] = this.reservationNumber;
                        j++;
                    }
                } // endfor
                this.reservationNumber++;
                this.availableSeatsT -= reservation.getnPassageiros();
            } // endif
            else if (freeInRow != -1) {
                for (int i = 0; i < reservation.getnPassageiros(); i++) {
                    seatsT[seatToStart+i] = this.reservationNumber;
                }
                this.reservationNumber++;
                this.availableSeatsT -= reservation.getnPassageiros();
            }
        } // endif -> turistica

        else if (reservation.getClassName() == ClassAviao.EXECUTIVA) {
            if(reservation.getnPassageiros()>=sizeE){
                System.out.println("ERROR: Not enough seats in plane to make reservation.");
                return;
            }
            if(availableSeatsE==0){
                System.out.println("ERROR:No available seats to make reservation.");
                return;
            }
            if(reservation.getnPassageiros()> this.availableSeatsE){
                System.out.println("ERROR: Not enough seats available for Executive reservation!");
                return;
            }
            for (int i = 0; i < sizeE; i++) {
                freeInRow = 0;
                if (seatsE[i] == 0 && i<sizeE-reservation.getnPassageiros()) {
                    for (int j = 0; j < reservation.getnPassageiros(); j++){
                        if(seatsE[i+j]==0){
                            freeInRow ++;
                        }else{
                            freeInRow=-1;
                        }
                    }//endfor

                    if(freeInRow==reservation.getnPassageiros()-1){
                        seatToStart=i;
                        break;
                    } else if (freeInRow == -1){
                        i+=(reservation.getnPassageiros()-1);
                    }
                }//endif
            }//endfor
            if (freeInRow == -1 && this.availableSeatsE >= reservation.getnPassageiros()) {
                int j = 0;
                for (int i = 0; i < sizeE; i++) {
                    if (seatsE[i] == 0 && j < reservation.getnPassageiros()) {
                        seatsE[i] = this.reservationNumber;
                        j++;
                    }
                } // endfor
                this.reservationNumber++;
                this.availableSeatsE -= reservation.getnPassageiros();
            } // endif
            else if (freeInRow != -1) {
                for (int i = 0; i < reservation.getnPassageiros(); i++) {
                    seatsE[seatToStart + i] = this.reservationNumber;
                }
                this.reservationNumber++;
                this.availableSeatsE -= reservation.getnPassageiros();
            }
        } // endif -> executiva
        else {
            System.err.println("ERROR: The reservation has an undefined class.");
            return;
        }
    }

    public void deleteReservation(int reservationCode) {
        for (int i = 0; i < sizeE; i++) {
            if (seatsE[i] == reservationCode) {
                seatsE[i] = 0;
                this.availableSeatsE++;
            }
        }
        for (int i = 0; i < sizeT; i++) {
            if (seatsT[i] == reservationCode) {
                seatsT[i] = 0;
                this.availableSeatsT++;
            }
        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classAviao == null) ? 0 : classAviao.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Aviao other = (Aviao) obj;
        if (classAviao != other.classAviao)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Aviao [classAviao=" + classAviao + "]";
    }

    

}
