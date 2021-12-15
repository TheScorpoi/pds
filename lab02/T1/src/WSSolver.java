package lab02.T1.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class WSSolver {
    public static void main(String[] args) {
        try{
            //==========WordSoup Format Verification============
            File file = new File("./lab02/T1/WSGout/"+args[0]);
            Scanner read = new Scanner(file);
            String line = read.nextLine();
            //Invalid Characters in Soup
            if(!line.matches("[A-Z]+")) {
                System.err.print("ERROR: The soup must be a matrix of uppercase alphabetical characters.\n");
                read.close();
                return;
            }
            //Soup size too large
            if(line.length()>40) {
                System.err.print("ERROR: The soup must be, at the maximum, 40 by 40.\n");
                read.close();
                return;
            }

            int len = line.length();
            char[][] sopa = new char[len][len];
            for(int i = 0; i < len; i++) sopa[0][i] = line.charAt(i);
            for(int i = 1; i < len; i++) {
                line = read.nextLine();
                //Soup isn't square or a character got past the previous verification
                if(!line.matches("[A-Z]+") || line.length()!=len) {
                    System.err.print("ERROR: The soup must be square (N by N) and a matrix of uppercase alphabetical characters.\n");
                    read.close();
                    return;
                }
                for(int j = 0; j < len; j++) sopa[i][j] = line.charAt(j); //copy to array the characters in soup
            }
            //=========Get Words from File================
            line = read.nextLine();
            while(read.hasNextLine()) {
                String nextLine = read.nextLine();
                if(nextLine.isEmpty()) {
                    System.err.print("ERROR: There can be no empty lines among the word search list.\n");
                    read.close();
                    return;
                }
                line = line + "\n" + nextLine;
            }

            //Splitting the String containing the words into a proper String array
            String[] palavras = line.split("[, ;\n]");
            //Saving a copy of the array to later know the original order the words were in
            String[] palavrasOriginais = Arrays.copyOf(palavras, palavras.length);

            //Sorting words by size to enable search for word-in-word
            Arrays.sort(palavras, (a, b) -> Integer.compare(b.length(), a.length()));

            //===============Solving the Word Soup=================
            Word[] solution = new Word[palavras.length];
            int idx = 0;
            for(String theWord : palavras) {
                boolean found;
                int counter = 0;
                String curWord = theWord.toUpperCase();
                //Verify if the provided word is valid
                if(theWord.equals(curWord) || !curWord.matches("[A-Z]+")) {
                    System.err.println("ERROR: The search words cannot be completely uppercase and they must also be alphabetical.");
                    read.close();
                    return;
                }
                //Looking for the word
                for(int i = 0; i < len; i++) {
                    for(int j = 0; j < len; j++) {
                        //Check if found the desired initial character
                        if(curWord.charAt(0) != sopa[i][j]) continue; //Redundant, for code readability
                        else {
                            int wordLen = curWord.length();
                            //Look for rest of words in all directions
                            for(Direction dir : Direction.values()) {
                                //Check if in a given direction, the word will fit in the soup
                                if(dir.getX()>0 && j+wordLen > len) continue;
                                else if(dir.getX()<0 && j+1 < wordLen) continue;
                                else if(dir.getY()>0 && i+wordLen > len) continue;
                                else if(dir.getY()<0 && i+1 < wordLen) continue;
                                else {
                                    found = true; //Might've found the word!
                                    int searchX = j + dir.getX();
                                    int searchY = i + dir.getY();
                                    for(int k=1; k < wordLen; k++) {
                                        if(curWord.charAt(k) != sopa[searchY][searchX]) {
                                            //Character is not the one we're looking for
                                            found = false;
                                            break; //go on to next direction/spot
                                        }
                                        else {
                                            //Apply direction modifier and move onto next spot
                                            searchX = searchX + dir.getX();
                                            searchY = searchY + dir.getY();
                                        }
                                    }
                                    if(found) {
                                        Word newWord = new Word(curWord, dir, wordLen, j + 1, i + 1);
                                        boolean overlaps = false;
                                        for(int l = 0; l < idx; l++) {
                                            //check for overlap among the words
                                            if(overlap(newWord,solution[l])) {
                                                overlaps = true;
                                                break;
                                            }
                                        }
                                        if(overlaps) continue; //keep searching for place for word
                                        else counter++;//For each word, should reset to zero
                                                       //If it's >1, there an duplicated word present
                                        if(counter>1) {
                                            System.err.print("ERROR: The soup contains a duplicated word. ("+curWord+")\n");
                                            read.close();
                                            return;
                                        }
                                        else if(counter==1) {
                                            solution[idx] = newWord;
                                            idx++; //next word
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(counter<1) {
                    System.err.print("ERROR: The soup doesn't contain one of the search words.\n"); //malformed soup :(
                    read.close();
                    return;
                }
            }
            //=============Exporting the Solution=============
            File out = new File("./lab02/T1/WSSout/output.txt");
            String toWrite="";
            try {
                FileWriter write = new FileWriter(out);
                //To present the results in the same order as they were in the original soup
                for(String palavraOriginal : palavrasOriginais) {
                    palavraOriginal = palavraOriginal.toUpperCase();
                    //Printing the table
                    for (Word sol : solution)
                        if(sol.getName().equals(palavraOriginal))
                            toWrite=sol.getName() + ": " + sol.getDir().toString() + ", " + sol.getLen() + ", (" + sol.getX() + "," + sol.getY() + ")\n";
                    write.write(toWrite);
                }
                write.write("\n");

                //Preparing the soup for printing
                char[][] sopaFinal = new char[len][len];
                for(int i = 0; i < len; i++) {
                    for (int j = 0; j < len; j++) {
                        sopaFinal[i][j] = '*';
                    }
                }
                //Inserting the words in the soup array
                for (Word palavra : solution) {
                    int i = palavra.getY() - 1;
                    int j = palavra.getX() - 1;
                    for (idx = 0; idx < palavra.getLen(); idx++) {
                        sopaFinal[i][j] = palavra.getName().charAt(idx);
                        i += palavra.getDir().getY();
                        j += palavra.getDir().getX();
                    }
                }

                //Printing the solution of the soup
                char charToWrite;
                for (int i = 0; i < len; i++) {
                    for (int j = 0; j < len; j++) {
                        charToWrite=sopaFinal[i][j];
                        write.write(charToWrite);
                    }
                    write.write("\n");
                }
                write.close();
            }catch(IOException ex){
                System.err.println("ERROR: Cannot create output file.");
            }
            read.close();
        } catch(FileNotFoundException e) {
            System.err.print("ERROR: Error reading file.\n");
        }
    }
    //=========Check if a small word was found "inside" a larger word==============
    //Returns true if there's overlap, false otherwise
    public static boolean overlap(Word a, Word b) {
        boolean ret = true;//initialized as true, if there's no word found it swaps to false
        Word bigWord = (a.getLen() > b.getLen()) ? a : b; //find out which is the big word
        Word smallWord = (a.getLen() <= b.getLen()) ? a : b;//and which is the small word
        String[] bigCoords = new String[bigWord.getLen()]; //coordinates of big word

        //formatting coordinates to compare
        for(int i = 0; i< bigWord.getLen(); i++) bigCoords[i] = "("+(bigWord.getX()+bigWord.getDir().getX()*i)+","+(bigWord.getY()+bigWord.getDir().getY()*i)+")";
        String[] smallCoords = new String[smallWord.getLen()];//coordinates of small word
        for(int i = 0; i< smallWord.getLen(); i++) smallCoords[i] = "("+(smallWord.getX()+smallWord.getDir().getX()*i)+","+(smallWord.getY()+smallWord.getDir().getY()*i)+")";

        //Check if big word contains big word
        for(int i = 0; i< smallWord.getLen(); i++) {
            boolean found = false;
            for(int j = 0; j < bigWord.getLen(); j++) {
                if(smallCoords[i].equals(bigCoords[j])) {
                    //There's overlap
                    found = true;
                    break;
                }
            }
            if(!found) {
                //There's no overlap
                ret = false;
                break;
            }
        }
        return ret;
    }
}
