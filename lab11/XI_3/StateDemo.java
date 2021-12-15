package XI_3;

import java.util.List;
import java.util.Scanner;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

//Pattern Used: State

public class StateDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Library library = new Library("Aveiro Books");
        
        library.add(new Book("Java Anti-Stress", "1234-124-123-1", "Omodionag", 2012));
        library.add(new Book("A Guerra dos Padroes", "3423-664-453-3", "Jorge Omel", 2001));
        library.add(new Book("A Procura da Luz", "7654-234-754-6", "Khumatkli", 2018));

        List<Book> bookList = library.returnList();

        while (true) {
            System.out.println("*** Library *** - " + library.getName());
            int i = 0;
            for (Book book : bookList) {
                i++;
                System.out.println(book);
                if (i == bookList.size()) {
                    book.setCount(0);
                }
            }
            System.out.println(">> <book>, <operation>: (1) register; (2) request; (3) return a Book; (4) reserve; (5) cancel");
            System.out.printf(">> ");
            try{
                String[] input = sc.nextLine().split(",");
                int bookChoose = Integer.parseInt(input[0]);
                int operation = Integer.parseInt(input[1]);
                Book book = bookList.get(bookChoose - 1);
                alterar(library, book, operation);
            }
            catch (NumberFormatException e){
                System.err.println("\nError: Wrong input format, please try again. Input example:\n>>1,1\n");
                continue;
            }
        }
    }

    public static void alterar(Library library, Book book, int operation) {
        switch (operation) {
            case 1:
                library.register(book);
                break;
            case 2:
                library.request(book);
                break;
            case 3:
                library.returnBook(book);
                break;
            case 4:
                library.reserve(book);
                break;
            case 5:
                library.cancelReserve(book);
                break;
        }
    }
}