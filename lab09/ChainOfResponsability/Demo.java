package ChainOfResponsability;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

public class Demo {

    public static void main(String[] args) {

        List<String> ordersList = new ArrayList<>();
        ordersList.add("veggie burger");
        ordersList.add("Pasta Carbonara");
        ordersList.add("PLAIN pizza, no toppings!");
        ordersList.add("sushi nigiri and sashimi");
        ordersList.add("salad with tuna");
        ordersList.add("strawberry ice cream and waffles dessert");

        Chef chef = new BurgerChef().setSuccessor(new DessertChef()
                .setSuccessor(new PastaChef().setSuccessor(
                        new PizzaChef().setSuccessor(new SushiChef()))));

        System.out.println("\n************************************\n********R*E*S*T*A*U*R*A*N*T*********\n************************************\n");

        for (String order : ordersList) {
            System.out.println("Can I please get a " + order + "?");
            chef.parse(order);
        }

        System.out.println("\n************************************\n************************************\n************************************\n");

    }

}
