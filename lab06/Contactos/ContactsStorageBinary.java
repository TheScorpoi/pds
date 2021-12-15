package Contactos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class ContactsStorageBinary implements ContactStorageInterface {
    
    private List<Contact> contacts = new ArrayList<Contact>();
    private File inputFile;

    public ContactsStorageBinary(File inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public List<Contact> loadContacts(){
        try(
            InputStream inputStream = new BufferedInputStream(new FileInputStream("./lab06/Contactos/Files/" + this.inputFile));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ){
            int bufferSize = 2048;
            byte[] buffer = new byte[bufferSize];
            while (inputStream.read(buffer) != -1) {
                outputStream.write(buffer);
                }
                outputStream.flush();
            byte[] byteArray = outputStream.toByteArray();
            String contacts = new String(byteArray, StandardCharsets.UTF_8);
            String[] contactsLst = contacts.split("\n");
            for (int i=0; i< contactsLst.length-1;i++){
                contactsLst[i].strip();
                if(!(contactsLst[i].equals(""))){
                    String[] input = contactsLst[i].split("\t");
                    Contact c = new Contact(input[0], Integer.parseInt(input[1]));
                    this.contacts.add(c);
                }
            }
            return this.contacts;
            }
        catch (FileNotFoundException e){
            System.err.println("ERROR: FILE NOT FOUND ===> " + inputFile);
            System.exit(1);
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    //------------ Save list to binary file -----------------------------------------
    @Override
    public boolean saveContacts(List<Contact> list) {

        
        
        StringBuilder extractContacts = new StringBuilder();
        for (Contact contacto : list) {
            extractContacts.append(contacto.toString());
        }
        try (InputStream inputStream = new BufferedInputStream(
                new ByteArrayInputStream(extractContacts.toString().getBytes()));
                OutputStream outputStream = new BufferedOutputStream(
                        new FileOutputStream("./lab06/Contactos/Files/SavedContacts.bin"));) {

            byte[] buffer = new byte[2048];

            while (inputStream.read(buffer) != -1) {
                outputStream.write(buffer);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }
    //---------------------------------------------------------------------------------

}
