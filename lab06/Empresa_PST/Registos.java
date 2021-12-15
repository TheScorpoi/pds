
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

 //Petiscos
public class Registos {
    
    private ArrayList<Empregado> empregados;

    public Registos(){
        empregados = new ArrayList<>();
    }

    public void insere(Empregado emp) {
        if (empregados.contains(emp)) {
            System.out.println("O empregado já se encontra na lista");
        } else {
            empregados.add(emp);
        }
    }
    
    public void remove(int codigo) {
        for (Empregado empregado : empregados) {
            if (empregado.codigo() == codigo) {
                empregados.remove(empregado);
                break;
            }
        }
    }
    
    public boolean isEmpregado(int codigo) {
        for (Empregado empregado : empregados) {
            if (empregado.codigo() == codigo) {
                return true;
            }
        }
        return false;
    }
    
    public List<Empregado> listaDeEmpregados(){

        return empregados;
    }

}
