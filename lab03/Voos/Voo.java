package lab03.Voos;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

public class Voo {
    
    private String code;
    public Aviao plane;
    public Map<Integer,Reserva> reservations = new HashMap<Integer,Reserva>();
    
    public Voo(String code, Aviao plane) {
        this.code = code;
        this.plane = plane;
    }

    public Aviao getAviao() {
        return this.plane;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void addReservations(Reserva reservation) {
        this.reservations.put(this.plane.getReservationNumber(), reservation);
        this.plane.makeReservation(reservation);
    }
    
    public void removeReservations(String[] code) {
        this.reservations.remove(Integer.parseInt(code[1]));
        this.plane.deleteReservation(Integer.parseInt(code[1].trim()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((plane == null) ? 0 : plane.hashCode());
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
        Voo other = (Voo) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (plane == null) {
            if (other.plane != null)
                return false;
        } else if (!plane.equals(other.plane))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Código de voo " + code + "\n");
        sb.append("Lugares disponiveis: \n");
        sb.append(plane.getAvailableSeatsT() + " lugares disponiveis em Turistica\n");
        if (plane.getAvailableSeatsE() == 0) {
            sb.append("Classe executiva nao tem lugares disponíveis no voo\n");
        } else {
            sb.append(plane.getAvailableSeatsE() + " lugares disponiveis em Executiva\n");
        }
        return sb.toString();
    }
    
    
}
