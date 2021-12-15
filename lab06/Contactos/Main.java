package Contactos;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        Agenda ag = new Agenda();

        //------------------------------- TXT -------------------------

        File filename = new File("contacts1.txt");
        ContactStorageInterface store = new ContactsImple(filename);
        System.out.println("******************* TXT FILE *******************");
        System.out.println("Open and load...");
        ag.openAndLoad(store);

        System.out.println("\ngetByName: " + ag.getByName("Daniel Silva"));

        System.out.println("\nAdd Angelina Bonita...");
        ag.add(new Contact("Angelina Bonita", 943456311));
        System.out.println("\ngetByName: " + ag.getByName("Angelina Bonita"));

        System.out.println("\nRemoving Daniel Silva...");
        ag.remove(ag.getByName("Daniel Silva"));

        System.out.printf("\nRaquel Costa exists?");
        if (ag.exist(ag.getByName("Raquel Costa")) == true) {
            System.out.printf(" Yes\n");
        } else {
            System.out.printf(" No\n");
        }

        System.out.printf("\nDaniel Silva exists?");
        if (ag.exist(ag.getByName("Daniel Silva")) == true) {
            System.out.printf(" Yes\n");
        } else {
            System.out.printf(" No\n");
        }

        //Add Daniel Silva to replace the Daniel Silva that remove before, and so that this works always
        ag.add(new Contact("Daniel Silva", 923456123));

        System.out.println("Save and Close...");
        ag.saveAndClose(store);

        //-------------------------------------------------------------

        System.out.println("**************** BINARY FILE **********************");
        String file = "contacts"; 
       

        //-------------------------------- BINARY --------------------------

        File fileN = new File(file);
        ContactStorageInterface csb = new ContactsImple(fileN);
        System.out.println("Open and load...");
        ag.openAndLoad(csb);


        System.out.println("getByName: " + (ag.getByName("Ze Manel")));

        System.out.println("Add Maria Joao");
        ag.add(new Contact("Maria Joao", 923234421));

        System.out.printf("\nMaria Joao exists?");
        if (ag.exist(ag.getByName("Maria Joao")) == true) {
            System.out.printf(" Yes\n");
        } else {
            System.out.printf(" No\n");
        }

        System.out.printf("\nCarlos Carl exists?");
        if (ag.exist(ag.getByName("Carlos Carl")) == true) {
            System.out.printf(" Yes\n");
        } else {
            System.out.printf(" No\n");
        }

        System.out.println("\nRemove Carlos Carl");
        ag.remove(ag.getByName("Carlos Carl"));

        System.out.printf("\nCarlos Carl exists?");
        if (ag.exist(ag.getByName("Carlos Carl")) == true) {
            System.out.printf(" Yes\n");
        } else {
            System.out.printf(" No\n");
        }

        System.out.printf("\nJoao Mexilhao exists?");
        if (ag.exist(ag.getByName("Joao Mexilhao")) == true) {
            System.out.printf(" Yes\n");
        } else {
            System.out.printf(" No\n");
        }

        ag.add(new Contact("Carlos Carl", 916767543));

        System.out.println("Save and Close...");
        ag.saveAndClose(csb);

        //------------------------------------------------------------------
        
    }
}
