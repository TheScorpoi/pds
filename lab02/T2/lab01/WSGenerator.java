package lab02.T2.lab01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WSGenerator {
    public static ArrayList<WordFound> wordsToGenerate = new ArrayList<WordFound>();
    public static int dimensions = 0, index = 0, overlapCounter = 0;
    public static String fileName = "none", output = "none";
    public static Random rand = new Random();
    public static Direction globalDirection;
    public static char[][] wordSearch = new char[40][40];
    public static void main(String[] args) {
        int op = 0;
        boolean sourceFile = false, outputFile = false, dimensionArg = false;
        for (String arg : args) { //Handle passed arguments
            switch (op) {
                case 1:
                    fileName = arg;
                    sourceFile = true;
                    op = 0;
                    break;
                case 2:
                    dimensions = Integer.parseInt(arg);
                    dimensionArg = true;
                    op = 0;
                    break;
                case 3:
                    output = arg;
                    outputFile = true;
                    op = 0;
                    break;
                default:
                    if(arg.equals("-i")) op = 1;
                    else if(arg.equals("-s")) op = 2;
                    else if(arg.equals("-o")) op = 3;
                    break;
            }
        }
        //Check if argument are correct
        if (!(sourceFile && dimensionArg) || (dimensions > 40 || dimensions <= 0)){ System.err.println("Invalid arguments passed"); System.exit(0);}
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXY";
        ArrayList<String> words = GetData();
        for(String word : words)
            GenerateWord(word);
        if (outputFile){ //if the user wants the output in a file
            try {
                FileWriter myWriter = new FileWriter("./lab02/T2/lab01/" + output);
                for (int i = 0; i < dimensions; i++){
                    for (int j = 0; j < dimensions; j++){ // build the Word Search
                        if (wordSearch[i][j] == '\0') myWriter.write(alphabet.charAt(rand.nextInt(alphabet.length())));
                        else myWriter.write(Character.toUpperCase(wordSearch[i][j]));
                    }
                    myWriter.write('\n');
                }
                for (WordFound word: wordsToGenerate)
                    myWriter.write(String.format("%s ", word.word));
                myWriter.close();
              } catch (IOException e) {
                System.out.println("Error writing to the file.");
                e.printStackTrace();
              }
        }
        else{ //in case the user wants output in terminal
            for (int i = 0; i < dimensions; i++){
                for (int j = 0; j < dimensions; j++){// build the Word Search
                    if (wordSearch[i][j] == '\0') System.out.print(alphabet.charAt(rand.nextInt(alphabet.length())));
                    else System.out.print(Character.toUpperCase(wordSearch[i][j]));
                }
                System.out.println();
            }
            for (WordFound word: wordsToGenerate)
                System.out.printf("%s ", word.word);
            }
        }
    static ArrayList<String> GetData(){ //get data from the file passed in args
        ArrayList<String> wordList = new ArrayList<String>();
        try {
            File file = new File("./lab02/T2/lab01/" + fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                for(String word : reader.nextLine().split("[ ;]")){
                    wordList.add(word);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("There was an error processing the Word Search File");
            e.printStackTrace();
        }
        return wordList;
    }

    static void GenerateWord(String wordToGenerate){
        wordsToGenerate.add(getDirection(wordToGenerate));
        WordFound word = wordsToGenerate.get(wordsToGenerate.size()-1);
            int x = word.x - 1;
            int y = word.y - 1;
            for (int h = 0; h < word.length; h++) {
                switch (word.direction) {
                case UP:
                    wordSearch[x - h][y] = word.word.charAt(h);
                    break;
                case DOWN:
                    wordSearch[x + h][y] = word.word.charAt(h);
                    break;
                case RIGHT:
                    wordSearch[x][y + h] = word.word.charAt(h);
                    break;
                case LEFT:
                    wordSearch[x][y - h] = word.word.charAt(h);
                    break;
                case UPRIGHT:
                    wordSearch[x - h][y + h] = word.word.charAt(h);
                    break;
                case UPLEFT:
                    wordSearch[x - h][y - h] = word.word.charAt(h);
                    break;
                case DOWNLEFT:
                    wordSearch[x + h][y - h] = word.word.charAt(h);
                    break;
                case DOWNRIGHT:
                    wordSearch[x + h][y + h] = word.word.charAt(h);
                    break;
                default:
                    break;
                }
        }
    }
    static boolean checkDirection(String word, int x, int y){
            boolean directionDefined = true, returnValue = true;
            ArrayList<Direction> allDirections = new ArrayList<>();
            Direction direction = Direction.RIGHT;
            for (Direction directions : Direction.values()) allDirections.add(directions);
            do{
                if (allDirections.isEmpty()) {returnValue = false; break;} //When all directions were tested and there is no possible fit check another x and y position
                direction = allDirections.get(rand.nextInt(allDirections.size())); //randomly test a position that hasn't been tested with the current x and y yet
                allDirections.remove(direction);
                overlapCounter = 0;
                for (int h = 0; h < word.length(); h++) { //check if its possible to fit the word in the defined direction
                    switch (direction) {
                        case UP:
                            if (!(x-h < dimensions && x-h >= 0 && (wordSearch[x-h][y] == '\0' || wordSearch[x-h][y] == word.charAt(h)))) directionDefined = false;
                            else if (wordSearch[x-h][y] == word.charAt(h)) overlapCounter++;
                            break;
                        case DOWN:
                            if (!(x+h < dimensions && (wordSearch[x+h][y] == '\0' || wordSearch[x+h][y] == word.charAt(h)))) directionDefined = false;
                            else if (wordSearch[x+h][y] == word.charAt(h)) overlapCounter++;
                            break;
                        case RIGHT:
                            if (!(y+h < dimensions && (wordSearch[x][y+h] == '\0' || wordSearch[x][y+h] == word.charAt(h)))) directionDefined = false;
                            else if (wordSearch[x][y+h] == word.charAt(h)) overlapCounter++;
                            break;
                        case LEFT:
                            if (!(y-h < dimensions && y-h >= 0 && (wordSearch[x][y-h] == '\0' || wordSearch[x][y-h] == word.charAt(h)))) directionDefined = false;
                            else if (wordSearch[x][y-h] == word.charAt(h)) overlapCounter++;
                            break;
                        case UPRIGHT:
                            if (!(x-h < dimensions && y+h < dimensions && x-h >= 0 && (wordSearch[x-h][y+h] == '\0' || wordSearch[x-h][y+h] == word.charAt(h)))) directionDefined = false;
                            else if (wordSearch[x-h][y+h] == word.charAt(h)) overlapCounter++;
                            break;
                        case UPLEFT:
                            if (!(x-h < dimensions && y-h < dimensions && x-h >= 0 && y-h >= 0 && (wordSearch[x-h][y-h] == '\0' || wordSearch[x-h][y-h] == word.charAt(h)))) directionDefined = false;
                            else if (wordSearch[x-h][y-h] == word.charAt(h)) overlapCounter++;
                            break;
                        case DOWNLEFT:
                            if (!(x+h < dimensions && y-h < dimensions && y-h >= 0 && (wordSearch[x+h][y-h] == '\0' || wordSearch[x+h][y-h] == word.charAt(h)))) directionDefined = false;
                            else if (wordSearch[x+h][y-h] == word.charAt(h)) overlapCounter++;
                            break;
                        case DOWNRIGHT:
                            if (!(x+h < dimensions && y+h < dimensions && y-h >= 0 && (wordSearch[x+h][y+h] == '\0' || wordSearch[x+h][y+h] == word.charAt(h)))) directionDefined = false;
                            else if (wordSearch[x+h][y+h] == word.charAt(h)) overlapCounter++;
                            break;
                        default:
                            break;
                    }
                    if (directionDefined == false) break;
                }
                if (overlapCounter == word.length()) return false; //if all characters are overlaped check another position
            } while (!directionDefined);
            if (returnValue == true) globalDirection = direction;
            return returnValue;
        }
        static WordFound getDirection(String word){ //try different x and y positions until there is a possible direction to fit the word
            int x, y;
            do{
                x = rand.nextInt(dimensions);
                y = rand.nextInt(dimensions);
            } while (!checkDirection(word, x, y)); 
            return new WordFound(word, word.length(), x, y, globalDirection, index++);
        }
    }
