package Contactos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Agenda implements ContactsInterface {

    List<Contact> contactList = new ArrayList<>();

    @Override
    public void openAndLoad(ContactStorageInterface store) {
        contactList = store.loadContacts();
    }

    @Override
    public void saveAndClose() {
        if (contactList.size() < 1) {
            System.err.println("ERROR: Contacts List need to be filled");
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("> ");
        String fileName = sc.nextLine();
        sc.close();

        try {
        ContactStorageInterface store = new ContactsImple(new File("./lab06/Contactos/Files/" + fileName));
        store.saveContacts(contactList);
            
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            System.exit(1);    
        }


    }

    @Override
    public void saveAndClose(ContactStorageInterface store) {

        if (contactList.size() < 1) {
            System.err.println("ERROR: Contacts List need to be filled");
        }

        store.saveContacts(contactList);
    }

    @Override
    public boolean exist(Contact contact) {
        if (contactList.contains(contact)) {
            return true;
        }
        return false;
    }

    @Override
    public Contact getByName(String name) {
        for (Contact contact : contactList) {
            if (contact.getNome().equals(name)) {
                return contact;
            }
        }
        return null;        
    }

    @Override
    public boolean add(Contact contact) {
        if (!contactList.contains(contact)) {
            contactList.add(contact);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Contact contact) {
        if (contactList.contains(contact)) {
            contactList.remove(contact);
            return true;
        }
        System.err.println("ERROR: Can't remove a contact that doesn't exist");
        return false;
    }

}
