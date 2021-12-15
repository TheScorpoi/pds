package ProcessadorTexto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TextReader implements TextInterface {

    private File fileName;
    List<String> lineLst = new ArrayList<String>();
    String sentence = "";
    
    public File getFileName() {
        return fileName;
    }

    public void setFileName(File fileName) {
        this.fileName = fileName;
    }

    public TextReader(File fileName) {
        this.fileName = fileName;
        try (Scanner sc = new Scanner(fileName)) {
            sc.useDelimiter(System.getProperty("line.separator"));
            if (sc.hasNext() == true) {
                this.lineLst.add(sc.next());
            }   
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public boolean hasNext() {
        return !(this.lineLst == null || this.lineLst.size() == 0);
    }

    @Override
    public String next() {
        if (this.hasNext() == true) {
                this.sentence = lineLst.get(0);
                this.sentence = lineLst.remove(0);
            return sentence;
        }
        return null;
    }

}
