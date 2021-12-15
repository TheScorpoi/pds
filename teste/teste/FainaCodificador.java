package teste;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FainaCodificador {
    
    static Map<String, String> cifraMap = new HashMap<String, String>();
    static Map<String, Integer> countLetter =  new HashMap<String, Integer>();
    public static void main(String[] args) {
        
        cifraMap.put("a", "t");cifraMap.put("b", "v");cifraMap.put("c", "n");cifraMap.put("d", "q");cifraMap.put("e", "h");cifraMap.put("f", "r");
        cifraMap.put("g", "z");cifraMap.put("h", "m");cifraMap.put("i", "x");cifraMap.put("j", "s");cifraMap.put("k", "a");cifraMap.put("l", "w");
        cifraMap.put("m", "f");cifraMap.put("n", "b");cifraMap.put("o", "p");cifraMap.put("p", "g");cifraMap.put("q", "c");cifraMap.put("r", "j");
        cifraMap.put("s", "i");cifraMap.put("t", "k");cifraMap.put("u", "d");cifraMap.put("v", "l");cifraMap.put("w", "e");cifraMap.put("x", "o");
        cifraMap.put("y", "u");cifraMap.put("z", "y");
        
        try (Scanner input = new Scanner(new File("./teste/faina_before.txt"))) {
            try (PrintWriter out = new PrintWriter(new File("./teste/faina_after.txt"))) {

                while (input.hasNext()) {
                    String line = input.nextLine();
                    String lineAfterCifra = "";
                    for (int i = 0; i < line.length(); i++) {
                        String letter = String.valueOf(line.charAt(i)).toLowerCase();
                        String letterAfterCifra = cifra(letter);
                        if (countLetter.containsKey(letterAfterCifra)) {
                            countLetter.put(letterAfterCifra, countLetter.get(letterAfterCifra) + 1);
                        } else {
                            countLetter.put(letterAfterCifra, 1);
                        }
                        lineAfterCifra += letterAfterCifra;
                    }
                    out.println(lineAfterCifra);
                }
                
                for (String key : cifraMap.keySet()) {
                    System.out.println(cifraMap.get(key) + " --> " + key);
                }
                //1 spot - 40.63327315210505, -8.660677981787714
                //2 spot - 40.639652128314026, -8.657040843157501
                //3 spot - 40.636543036680685, -8.65343065801241
            } catch (FileNotFoundException e) {
                System.err.println("ERROR: File not found");
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File not Found");
        }
    }

    public static String cifra(String letter) {
        if (cifraMap.containsKey(letter)) {
            return cifraMap.get(letter);
        }
        return " ";
    }
}
