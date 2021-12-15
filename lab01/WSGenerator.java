package lab01;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.lang.Integer;




public class WSGenerator {
    public static void main(String[] args){
        List<String> wordLst = new ArrayList<>();
        int size=0;
        int i=0;
        String iFile="", oFile="";

        //------- Check Input args
        if(args.length == 0) {
            System.err.println("Error: No input arguments.\n Args:\n   -i <input-file>\n   -s <word-soup-size>\n   -o <output-file> [default:'sopa.txt']");
            System.exit(0);
        }

        if(!Arrays.stream(args).anyMatch("-i"::equals)){
            System.err.println("Error: Input file is mandatory!");
            System.exit(0);
        }
        if(!Arrays.stream(args).anyMatch("-s"::equals)){
            System.err.println("Error: Soup size is mandatory!");
            System.exit(0);
        }
        for (String arg : args) {
            switch (arg) {
                case "-i":
                    i++;
                    if (args[i] == "-o" || args[i] == "-s"){
                        System.err.println("Error: Please define a valid input file.");
                        System.exit(0);
                    }
                    iFile = args[i];
                    break;

                case "-s":
                    i++;
                    if (args[i] == "-o"){
                        System.err.println("Error: Please define a valid size.");
                        System.exit(0);
                    }
                    size = Integer.parseInt(args[i]);
                    break;

                case "-o":
                    i++;
                    oFile = args[i];
                    break;
            
                default:
                    i++;
                    break;
            }
        }



        //----- Get word list from input file
        char[][] soup = new char[size][size];
        try {
            File wFile = new File("Ficheiros/WordLists/"+iFile);
            Scanner scf = new Scanner(wFile);
            while (scf.hasNextLine()) {
                String line = scf.nextLine();
                String[] line2 = line.split("[, ; ]");
                for (String str : line2) {
                    if(str.equals("")) continue;
                    wordLst.add(str);
                }
            }
            scf.close();
            
        } catch (FileNotFoundException e) {
            String message = "Error: File not found";
            throw new RuntimeException(message);
        } 
        
        
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        List<String> allDirections = new ArrayList<>();
        allDirections.add("right");
        allDirections.add("left");
        allDirections.add("up");
        allDirections.add("down");
        allDirections.add("right-up");
        allDirections.add("right-down");
        allDirections.add("left-up");
        allDirections.add("left-down");
        

        // try to generate a valid index for a word
        Random randomN = new Random();
        int[] index = new int[2];
        int giveUp = 0;
        for(String word : wordLst){
            if(word.matches("[ \n\0\r]")) continue;
            giveUp = 0;
            char[] wordx = word.toCharArray();
            while(true){
                giveUp++;
                String dir = allDirections.get(randomN.nextInt(allDirections.size()));
                index = genIndex(dir, size, wordx.length);
                if(checkWord(wordx,index, soup, dir)){
                    soup = addWord(wordx, size, index, soup, dir);

                break;
                }
                if(giveUp>=1000000){
                    System.err.printf("Generator gave up after trying to generate an index and direction for the %s word 1000000 times.\n", word);
                    System.exit(0);
                }
            }
        }
        //write word soup to a file
        try {
            if(oFile.equals("")) oFile="sopa.txt";
            PrintWriter pw = new PrintWriter("Ficheiros/WordSoups/"+oFile, "UTF-8");
            for (int charY = 0; charY < size; charY++) {
                for (int charX = 0; charX < size; charX++) {

                    if(charX%size==0 && charY!=0) pw.write("\n");//if it reaches the size, insert new line, 0%size is also 0 so we have to avail it
                    if(soup[charY][charX]=='\u0000') pw.write(alphabet[randomN.nextInt(alphabet.length)] & 0x5f); // related to ASCII code, lowercase char & 0x5f "translates" it to uppercase
                    else pw.write(soup[charY][charX] & 0x5f);
                }  
            }
            pw.write("\n");
            //write list of words separated by ;
            for (String word : wordLst) {
                pw.write(word+";");
            }
            pw.close();
        } catch (IOException e) {
           throw new RuntimeException();
        }
        



        
    }

    //generates a starting index for the word, depending on it's size, meaning it'll never generate an index that is out of bounds
    private static int[] genIndex(String direction, int size, int wSize){
        Random randomN = new Random();
        int[] index = new int[2]; // index[0]--> Y-axis   index[1]--> X-axis
            switch (direction) {
                case "up":
                    index[0] = randomN.nextInt(size-wSize)+wSize;
                    index[1] = randomN.nextInt(size);
                    break;


                case "down":
                    index[0] = randomN.nextInt(size-wSize);
                    index[1] = randomN.nextInt(size);
                    break;

                case "right":
                    index[1] = randomN.nextInt(size-wSize);
                    index[0] = randomN.nextInt(size);
                break;

                    
                case "left":
                    index[1] = randomN.nextInt(size-wSize)+wSize;
                    index[0] = randomN.nextInt(size);
                break;

                    
                case "right-up":
                    index[0] = randomN.nextInt(size-wSize)+wSize;
                    index[1] = randomN.nextInt(size-wSize);
                break;

                    
                case "left-up":
                    index[1] = randomN.nextInt(size-wSize)+wSize;
                    index[0] = randomN.nextInt(size-wSize)+wSize;
                break;
                    
                case "right-down":
                    index[1] = randomN.nextInt(size-wSize);
                    index[0] = randomN.nextInt(size-wSize);
                break;
                    
                case "left-down":
                    index[0] = randomN.nextInt(size-wSize);
                    index[1] = randomN.nextInt(size-wSize)+wSize;
                break;
                    
            }
        return index;
    }

    //checks if the spaces in the current path are empty or check if letters to place match with existing ones 
    // '\u0000' is equivalent to having an empty char
    private static boolean checkWord(char[] wordx, int[] index, char[][] soup, String direction){
        switch (direction) {
            case "up":
            for (int i = 0; i < wordx.length; i++) {
               if(soup[index[0]-i][index[1]]== '\u0000' || soup[index[0]-i][index[1]]== wordx[i]){
                if(i==wordx.length-1) return true; //if we reach the word size -1 (for index purposes), then we can place the word there
                continue;
               } else break;
            } break;

            case "down":
            for (int i = 0; i < wordx.length; i++) {
                if(soup[index[0]+i][index[1]]== '\u0000' || soup[index[0]+i][index[1]]== wordx[i]){
                 if(i==wordx.length-1) return true;
                 continue;
                } else break;
             } break;

            case "right":
            for (int i = 0; i < wordx.length; i++) {
                if(soup[index[0]][index[1]+i]== '\u0000' || soup[index[0]-i][index[1]]== wordx[i]){
                 if(i==wordx.length-1) return true;
                 continue;
                }else break;
             } break;
                
            case "left":
            for (int i = 0; i < wordx.length; i++) {
                if(soup[index[0]][index[1]-i]== '\u0000' || soup[index[0]][index[1]-i]== wordx[i]){
                    
                 if(i==wordx.length-1) return true;
                 continue;
                }else break;
             } break;
                
            case "right-up":
            for (int i = 0; i < wordx.length; i++) {
                if(soup[index[0]-i][index[1]+i]== '\u0000' || soup[index[0]-i][index[1]+i]== wordx[i]){
                    
                 if(i==wordx.length-1) return true;
                 continue;
                }else break;
             } break;
                
            case "left-up":
            for (int i = 0; i < wordx.length; i++) {
                if(soup[index[0]-i][index[1]-i]== '\u0000' || soup[index[0]-i][index[1]-i]== wordx[i]){
                    
                 if(i==wordx.length-1) return true;
                 continue;
                }else break;
             } break;
                
            case "right-down":
            for (int i = 0; i < wordx.length; i++) {
                if(soup[index[0]+i][index[1]+i]== '\u0000' || soup[index[0]+i][index[1]+i]== wordx[i]){
                    
                 if(i==wordx.length-1) return true;
                 continue;
                }else break;
             } break;
                
            case "left-down":
            for (int i = 0; i < wordx.length; i++) {
                if(soup[index[0]+i][index[1]-i]== '\u0000' || soup[index[0]+i][index[1]-i]== wordx[i]){
                    
                 if(i==wordx.length-1) return true;
                 continue;
                }else break;
             } break;

        }

        return false;
    }

    //adds word in current direction to the word soup
    private static char[][] addWord(char[] wordx, int size,int[] index, char[][] soup, String direction) {
        switch (direction) {
            case "up":
            for (int i = 0; i < wordx.length; i++) {
               soup[index[0]-i][index[1]] = wordx[i]; 
            }
            break;

            case "down":
            for (int i = 0; i < wordx.length; i++) {
                soup[index[0]+i][index[1]] = wordx[i]; 
             }
             break;

            case "right":
            for (int i = 0; i < wordx.length; i++) {
                soup[index[0]][index[1]+i] = wordx[i]; 
             }
             break;
                
            case "left":
            for (int i = 0; i < wordx.length; i++) {
                soup[index[0]][index[1]-i] = wordx[i]; 
             }
             break;
                
            case "right-up":
            for (int i = 0; i < wordx.length; i++) {
                soup[index[0]-i][index[1]+i] = wordx[i]; 
             }
             break;
                
            case "left-up":
            for (int i = 0; i < wordx.length; i++) {
                soup[index[0]-i][index[1]-i] = wordx[i]; 
             }
             break;
                
            case "right-down":
            for (int i = 0; i < wordx.length; i++) {
                soup[index[0]+i][index[1]+i] = wordx[i]; 
             }
             break;
                
            case "left-down":
            for (int i = 0; i < wordx.length; i++) {
                soup[index[0]+i][index[1]-i] = wordx[i]; 
             }
             break;
        
        
            }
        return soup;
    
}

}


