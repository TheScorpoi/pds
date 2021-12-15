package lab02.T2.lab01;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.*;
import java.util.stream.Collectors;

public class WSSolver {
    public static char[][] wordSearch = new char[40][40]; // Word Soup given
    public static char[][] wordsFound = new char[40][40]; // Soup with the words found
    public static List<String> wordsToFind = new ArrayList<>(); // ArrayList to save the words from the list
    public static ArrayList<WordFound> solution = new ArrayList<>(); //
    public static int dimension = 0; // dimension of the word search
    private static int initialX, initialY;
    public static String fileName = "none";

    public static void main(String[] args) {
        for (String arg : args) {
            fileName = "./lab02/T2/lab01/" + arg; //ALTERACAO DO NOME DO FICHEIRO PARA CORRER DURANTE A AVALIACAO
            break;
        }
        GetData();
        for (String word : wordsToFind.stream().sorted(Comparator.comparingInt(String::length).reversed())
                .collect(Collectors.toList()))
            for (int i = 0; i < dimension; i++)
                for (int j = 0; j < dimension; j++)
                    if (Character.toUpperCase(word.charAt(0)) == wordSearch[i][j])
                        SearchWord(word, i, j, 1, Direction.RIGHT, wordsToFind.indexOf(word), 1);
        if (wordsToFind.size() > solution.size())
            ErrorFound('F');
        else if (wordsToFind.size() < solution.size())
            ErrorFound('G');
        Collections.sort(solution, new NumberComparator()); // Sort list
        for (WordFound wordSolution : solution) { // Print word list
            System.out.println(wordSolution.toString());
        }
        System.out.println(); // Print word Jumble
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(wordsFound[i][j]);
            }
            System.out.print("\n");
        }
    }

    static void GetData() {
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            int i = 0, j, count = 0;
            while (reader.hasNextLine()) {
                count++;
                j = 0;
                String line = reader.nextLine();
                if (dimension == 0)
                    dimension = line.length(); // the word search should be dimension x dimension
                if (line.isEmpty())
                    ErrorFound('E'); // In case the line is empty
                if (count <= dimension) { // if true then we still should be in the word search
                    if (line.length() != dimension || dimension > 40)
                        ErrorFound('D'); // check the soup dimensions
                    for (char c : line.toCharArray()) {
                        if (Character.isLetter(c) && Character.isUpperCase(c)) { // should be a Capitalized Letter
                            wordsFound[i][j] = '.'; // Fill the solution matrix
                            wordSearch[i][j++] = c; // Fill the initial soup matrix
                        } else
                            ErrorFound((Character.isLetter(c)) ? 'C' : 'A'); // If is letter then it's not Capital, else
                                                                             // is a number
                    }
                } else {
                    // Check if the next line is supposed to be from the soup or not, and if yes,
                    // then report error.
                    boolean control = true; // If not, then is considered as a word to find
                    for (int h = 0; h < line.length(); h++) {
                        if (Character.isLowerCase(line.charAt(h))) {
                            control = false;
                            break;
                        }
                    }
                    if (control == true)
                        ErrorFound('D');
                    wordsToFind.addAll(Arrays.asList(line.split("[, ;]")));
                }
                i++;
            }

            reader.close();
            // Check if the list is valid
            int somaMaiusculas;
            for (String word : wordsToFind) {
                somaMaiusculas = 0;
                for (int h = 0; h < word.length(); h++) {
                    if (Character.isUpperCase(word.charAt(h))) {
                        somaMaiusculas++;
                    } else if (!Character.isLetter(word.charAt(h))) {
                        somaMaiusculas = word.length();
                        break;
                    }
                }
                if (somaMaiusculas == word.length())
                    ErrorFound('B');
            }
        } catch (FileNotFoundException e) {
            System.out.println("There was an error processing the Word Search File");
            e.printStackTrace();
        }
    }

    static void SearchWord(String word, int x, int y, int index, Direction direction, int number, int overlapCounter) {
        if (overlapCounter == word.length())
            return;
        if (index >= word.length() && overlapCounter < word.length()) { // If the word is found and not overlaping then
                                                                        // we write the word
            solution.add(new WordFound(word, word.length(), initialX, initialY, direction, number));
            for (int h = 0; h < word.length(); h++) { // Writing the solution
                switch (direction) {
                case UP:
                    wordsFound[x + h][y] = wordSearch[x + h][y];
                    break;
                case DOWN:
                    wordsFound[x - h][y] = wordSearch[x - h][y];
                    break;
                case RIGHT:
                    wordsFound[x][y - h] = wordSearch[x][y - h];
                    break;
                case LEFT:
                    wordsFound[x][y + h] = wordSearch[x][y + h];
                    break;
                case UPRIGHT:
                    wordsFound[x + h][y - h] = wordSearch[x + h][y - h];
                    break;
                case UPLEFT:
                    wordsFound[x + h][y + h] = wordSearch[x + h][y + h];
                    break;
                case DOWNLEFT:
                    wordsFound[x - h][y + h] = wordSearch[x - h][y + h];
                    break;
                case DOWNRIGHT:
                    wordsFound[x - h][y - h] = wordSearch[x - h][y - h];
                    break;
                default:
                    break;
                }
            }
        } else if (index >= word.length()) {
            return;
        } // If the word overlaps we don't return nothing
        else if (index == 1) { // WE start by checking every direction
            initialX = x;
            initialY = y;
            if (y + 1 < dimension)
                if (wordSearch[x][y + 1] == Character.toUpperCase(word.charAt(index))) {
                    if (wordsFound[x][y] != '.')
                        overlapCounter++;
                    SearchWord(word, x, y + 1, index + 1, Direction.RIGHT, number, overlapCounter);
                }
            if (y - 1 < dimension && y - 1 >= 0)
                if (wordSearch[x][y - 1] == Character.toUpperCase(word.charAt(index))) {
                    if (wordsFound[x][y] != '.')
                        overlapCounter++;
                    SearchWord(word, x, y - 1, index + 1, Direction.LEFT, number, overlapCounter);
                }
            if (x - 1 < dimension && x - 1 >= 0)
                if (wordSearch[x - 1][y] == Character.toUpperCase(word.charAt(index))) {
                    if (wordsFound[x][y] != '.')
                        overlapCounter++;
                    SearchWord(word, x - 1, y, index + 1, Direction.UP, number, overlapCounter);
                }
            if (x + 1 < dimension)
                if (wordSearch[x + 1][y] == Character.toUpperCase(word.charAt(index))) {
                    if (wordsFound[x][y] != '.')
                        overlapCounter++;
                    SearchWord(word, x + 1, y, index + 1, Direction.DOWN, number, overlapCounter);
                }
            if (x - 1 < dimension && y + 1 < dimension && x - 1 >= 0)
                if (wordSearch[x - 1][y + 1] == Character.toUpperCase(word.charAt(index))) {
                    if (wordsFound[x][y] != '.')
                        overlapCounter++;
                    SearchWord(word, x - 1, y + 1, index + 1, Direction.UPRIGHT, number, overlapCounter);
                }
            if (x - 1 < dimension && y - 1 < dimension && x - 1 >= 0 && y - 1 >= 0)
                if (wordSearch[x - 1][y - 1] == Character.toUpperCase(word.charAt(index))) {
                    if (wordsFound[x][y] != '.')
                        overlapCounter++;
                    SearchWord(word, x - 1, y - 1, index + 1, Direction.UPLEFT, number, overlapCounter);
                }
            if (x + 1 < dimension && y + 1 < dimension && y - 1 >= 0)
                if (wordSearch[x + 1][y - 1] == Character.toUpperCase(word.charAt(index))) {
                    if (wordsFound[x][y] != '.')
                        overlapCounter++;
                    SearchWord(word, x + 1, y - 1, index + 1, Direction.DOWNLEFT, number, overlapCounter);
                }
            if (x + 1 < dimension && y + 1 < dimension)
                if (wordSearch[x + 1][y + 1] == Character.toUpperCase(word.charAt(index))) {
                    if (wordsFound[x][y] != '.')
                        overlapCounter++;
                    SearchWord(word, x + 1, y + 1, index + 1, Direction.DOWNRIGHT, number, overlapCounter);
                }
        } else { // we need to keep going on the same direction we selected before
            switch (direction) {
            case UP:
                if (x - 1 < dimension && x - 1 >= 0)
                    if (wordSearch[x - 1][y] == Character.toUpperCase(word.charAt(index))) {
                        if (wordsFound[x][y] != '.')
                            overlapCounter++;
                        SearchWord(word, x - 1, y, index + 1, Direction.UP, number, overlapCounter);
                    }
                break;
            case DOWN:
                if (x + 1 < dimension)
                    if (wordSearch[x + 1][y] == Character.toUpperCase(word.charAt(index))) {
                        if (wordsFound[x][y] != '.')
                            overlapCounter++;
                        SearchWord(word, x + 1, y, index + 1, Direction.DOWN, number, overlapCounter);
                    }
                break;
            case RIGHT:
                if (y + 1 < dimension)
                    if (wordSearch[x][y + 1] == Character.toUpperCase(word.charAt(index))) {
                        if (wordsFound[x][y] != '.')
                            overlapCounter++;
                        SearchWord(word, x, y + 1, index + 1, Direction.RIGHT, number, overlapCounter);
                    }
                break;
            case LEFT:
                if (y - 1 < dimension && y - 1 >= 0)
                    if (wordSearch[x][y - 1] == Character.toUpperCase(word.charAt(index))) {
                        if (wordsFound[x][y] != '.')
                            overlapCounter++;
                        SearchWord(word, x, y - 1, index + 1, Direction.LEFT, number, overlapCounter);
                    }
                break;
            case UPRIGHT:
                if (x - 1 < dimension && y + 1 < dimension && x - 1 >= 0)
                    if (wordSearch[x - 1][y + 1] == Character.toUpperCase(word.charAt(index))) {
                        if (wordsFound[x][y] != '.')
                            overlapCounter++;
                        SearchWord(word, x - 1, y + 1, index + 1, Direction.UPRIGHT, number, overlapCounter);
                    }
                break;
            case UPLEFT:
                if (x - 1 < dimension && y - 1 < dimension && x - 1 >= 0 && y - 1 >= 0)
                    if (wordSearch[x - 1][y - 1] == Character.toUpperCase(word.charAt(index))) {
                        if (wordsFound[x][y] != '.')
                            overlapCounter++;
                        SearchWord(word, x - 1, y - 1, index + 1, Direction.UPLEFT, number, overlapCounter);
                    }
                break;
            case DOWNLEFT:
                if (x + 1 < dimension && y + 1 < dimension && y - 1 >= 0)
                    if (wordSearch[x + 1][y - 1] == Character.toUpperCase(word.charAt(index))) {
                        if (wordsFound[x][y] != '.')
                            overlapCounter++;
                        SearchWord(word, x + 1, y - 1, index + 1, Direction.DOWNLEFT, number, overlapCounter);
                    }
                break;
            case DOWNRIGHT:
                if (x + 1 < dimension && y + 1 < dimension)
                    if (wordSearch[x + 1][y + 1] == Character.toUpperCase(word.charAt(index))) {
                        if (wordsFound[x][y] != '.')
                            overlapCounter++;
                        SearchWord(word, x + 1, y + 1, index + 1, Direction.DOWNRIGHT, number, overlapCounter);
                    }
                break;
            default:
                break;
            }
        }
    }

    static void ErrorFound(char error) {
        switch (error) {
        case 'A':
            System.out.println("Numeric Characters found in the file");
            break;
        case 'B':
            System.out.println("Invalid words in the list");
            break;
        case 'C':
            System.out.println("Non Capitalized Letters");
            break;
        case 'D':
            System.out.println("Invalid Word Search Dimensions");
            break;
        case 'E':
            System.out.println("Empty Line found in the file");
            break;
        case 'F':
            System.out.println("At least one word from the list isn't in the Word Jumble");
            break;
        case 'G':
            System.out.println("Soup has at least 2 possibilities for one word");
            break;
        default:
            System.out.println("Error");
            break;
        }
        System.exit(0);
    }
}