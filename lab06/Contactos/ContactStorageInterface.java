package Contactos;

import java.util.List;

public interface ContactStorageInterface {
    
    public List<Contact> loadContacts();
    public boolean saveContacts(List<Contact> list);
}
