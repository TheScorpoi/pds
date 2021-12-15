package Cabazes;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

//Pattern used: Composite

public class CabazesTest {

    public static void main(String[] args) {
    
        Caixa principal = new Caixa("Principal", 4);
        Caixa top = new Caixa("Topo", 2);
        Caixa bot = new Caixa("Especialidades", 2);
        top.add(new Bedida("Vinho Reserva UA 2017", 6));
        top.add(new Bedida("Vinho Reserva UA 2018", 6));
        principal.add(top);
        principal.add(bot);
        bot.add(new Conserva("Atum a Algarvia", 3));
        bot.add(new Doce("Morango", 2));
        top.add(new Caixa("Interior", 1));
        top.add(new Conserva("Sardinhas em Azeite", 5));
        principal.draw();

    }  
}
