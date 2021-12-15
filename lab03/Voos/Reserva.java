package lab03.Voos;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

public class Reserva {

    private ClassAviao classe;
    private int nPassageiros;

    public Reserva(ClassAviao classe, int nPassageiros) {
        this.nPassageiros = nPassageiros;
        this.classe = classe;
    }

    public int getnPassageiros() {
        return nPassageiros;
    }

    public void setnPassageiros(int nPassageiros) {
        this.nPassageiros = nPassageiros;
    }

    public ClassAviao getClassName() {
        return this.classe;
    }

    public void setClasse(ClassAviao classe) {
        this.classe = classe;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classe == null) ? 0 : classe.hashCode());
        result = prime * result + nPassageiros;
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
        Reserva other = (Reserva) obj;
        if (classe != other.classe)
            return false;
        if (nPassageiros != other.nPassageiros)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reserva [classe=" + classe + ", nPassageiros=" + nPassageiros + "]";
    }
}
