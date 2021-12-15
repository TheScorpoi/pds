package Contactos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsStorageTxt extends ContactsImple {

    private File filename;
    private List<Contact> contactsList = new ArrayList<>();

    public ContactsStorageTxt(File file) {
        super(file);
        this.filename = file;
    }
    
    public List<Contact> loadContacts() {
        try (Scanner sc = new Scanner(new File("./lab06/Contactos/Files/" + filename))) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] tab = sc.nextLine().split("\t");
                Contact contact = new Contact(tab[0], Integer.parseInt(tab[1]));
                contactsList.add(contact);
            }
            return contactsList;
            
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: FILE NOT FOUND");
            System.exit(1);
        }
        return null;
    }

    public boolean saveContacts(List<Contact> list) {
        
        try (PrintWriter out = new PrintWriter(new File("./lab06/Contactos/Files/" + filename))) {
            out.println("NAME            NUMBER");
            for (Contact contact : list) {
                out.println(contact);
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: FILE NOT FOUND");
            System.exit(1);
        }
        return false;
    }    
}
