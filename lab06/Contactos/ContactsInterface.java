package Contactos;

public interface ContactsInterface {
    
    public void openAndLoad(ContactStorageInterface store);
    public void saveAndClose();
    public void saveAndClose(ContactStorageInterface store);
    public boolean exist(Contact contact);
    public Contact getByName(String name);
    public boolean add(Contact contact);
    public boolean remove(Contact contact);

}
