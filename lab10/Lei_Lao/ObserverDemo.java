package Lei_Lao;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */


public class ObserverDemo {

    public static void main(String[] args) {

        System.out.println("Welcome!");
        System.out.println("Adding Productos, Clientes, and Gestores\n");
        wait(1000);

        Produto[] produto = { new Produto("Couve", 45), new Produto("Laranja", 22), new Produto("Alface", 20),
                new Produto("Cenoura", 10), new Produto("Limao", 5) };

        Cliente[] cliente = { new Cliente("Sofia"), new Cliente("Sara"), new Cliente("Diana") };

        Gestor gestor1 = new Gestor("Alfredo");

        produto[0].attach(gestor1);
        produto[1].attach(gestor1);
        produto[2].attach(gestor1);
        produto[3].attach(gestor1);
        produto[4].attach(gestor1);

        gestor1.start(produto[0]);
        gestor1.start(produto[1]);
        gestor1.start(produto[2]);
        gestor1.start(produto[3]);
        gestor1.start(produto[4]);

        produto[0].attach(cliente[0]);
        produto[0].attach(cliente[1]);

        produto[1].attach(cliente[2]);

        produto[3].attach(cliente[1]);

        cliente[0].makeAnOffer(produto[0], 60);
        wait(450);
        cliente[1].makeAnOffer(produto[1], 30);
        wait(450);
        cliente[2].makeAnOffer(produto[2], 70);
        wait(500);
        gestor1.sellItem(produto[2], produto[2].getHighestBidder().getName(), produto[2].getHighestBid());
        gestor1.sellItem(produto[1], produto[1].getHighestBidder().getName(), produto[1].getHighestBid());
        gestor1.sellItem(produto[0], produto[0].getHighestBidder().getName(), produto[0].getHighestBid());
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
