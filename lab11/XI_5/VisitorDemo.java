package XI_5;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

// Pattern Used: Visitor
public class VisitorDemo {
    public static void main(String[] args) {
        System.out.printf("----------------------------------------------------------\n");

        SizeOfDiretory calculator = new SizeOfDiretory();

        if (args.length == 1) {
            String path = args[0];
            System.out.printf("----------------------------------------------------------\nTOTAL:%52s\n",calculator.getSizeOfDirectory(path, false) + " (kB)");

        } else if (args.length == 2 && args[0].equals("-r")) {
            String path = args[1];
            System.out.printf("----------------------------------------------------------\nTOTAL:%52s\n",calculator.getSizeOfDirectory(path, true) + " (kB)");
        
        } else {
            System.out.println("HELP - USAGE\njava VisitorDemo <path> \t\t <path> to calculate size of the directory\njava VisitorDemo -r <path> \t\t recursively calculate the size of the folders starting in <path>");
            System.exit(1);
        }
    }
    

}
