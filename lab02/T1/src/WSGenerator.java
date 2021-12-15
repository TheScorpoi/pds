package lab02.T1.src;

import java.util.*;
import java.io.*;

public class WSGenerator {
    public static void main(String[] args) {
        // Initial verification on amount of arguments provided
        if (args.length > 6 || args.length < 2) {
            System.err.print(
                    "Incorrect use of function.\n  -i [read_file] (mandatory)\n  -s [size] (default: 20)\n  -o [write_file] (default: out2.txt)\n");
            return;
        }
        // ========Initialization=========
        // Creating the file and scanner objects to use later
        // with a large scope
        File file = null;
        Scanner read = null;
        // Default variable values
        // which will be used if no custom value is provided
        boolean hasFile = false;
        int size = 20;
        String output = "./lab02/T1/WSSout/out2.txt";

        // =========Argument&Option Parsing===========
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
            // Use a specific file as input -> Go fetch the words from there
            case ("-i"): {
                try {
                    try {
                        String input;
                        if (args[i + 1].contains(".txt")) // Adds the file extension if only the
                            input = args[i + 1]; // file name was provided
                        else
                            input = args[i + 1] + ".txt";
                        file = new File("./lab02/T1/WSGin/" + input);
                        read = new Scanner(file);
                        hasFile = true;
                    } catch (FileNotFoundException e) {
                        System.err.println("ERROR: Invalid Input File.");
                        return;
                    }
                } catch (IndexOutOfBoundsException e) {
                    // If the user includes the option but not the argument, this would go out of
                    // bounds
                    System.err.println("ERROR: Necessary argument missing! Usage: -i [input_file_name]");
                    return;
                }
                break;
            }
            // Define size of the soup (height and width)
            case ("-s"): {
                try {
                    try {
                        size = Integer.parseInt(args[i + 1]);
                    } catch (NumberFormatException num) {
                        // user pls
                        System.err.println("ERROR: Invalid size, must be an integer.");
                        return;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("ERROR: Necessary argument missing! Usage: -s [size]");
                    return;
                }
                break;
            }
            // Specify the desired file the generator will output the completed soup to
            case ("-o"): {
                try {
                    if (args[i + 1].contains(".txt")) // See: -i
                        output = args[i + 1];
                    else
                        output = args[i + 1] + ".txt";
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("ERROR: Necessary argument missing! Usage: -o [output_file_name]");
                    return;
                }
            }
            }
        }
        if (!hasFile) { // there must be an input file!
            System.err.print("ERROR: Input file option is mandatory. Usage: -i [input_file_name]\n");
            return;
        }
        // Debug: System.out.print("Input file is: " + file.getName() + "; Output file
        // is: " + output + "; Size is: " + size + "\n");

        // =========Get words from Input file==============
        String[] tmp_words; // Holds the line we're now reading
        ArrayList<String> words = new ArrayList<>();// Holds the actual words
        while (read.hasNextLine()) {
            tmp_words = read.nextLine().split("[;:, ]");
            for (String w : tmp_words) {
                if (w.length() > size) { // If the word is larger than size it won't fit the soup in any way
                    System.err.print(
                            "ERROR: Word in input file is bigger than the specified size allows. Try increasing the size argument.\n");
                    return;
                }
                if (!w.isEmpty())
                    words.add(w);
            }
        }
        // =====Soup Initialization=========
        char[][] soup = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                soup[i][j] = '*';
            }
        }
        // ==========Inserting the words in soup==========
        soup = wordInsertion(soup, words, 0, size); // Defined in line 134
        if (soup[0][0] == '+') {
            System.err.print("ERROR: Word puzzle is impossible. Try increasing the size or cutting some words.\n");
            return; // Not necessary but helps with code readability
        } else {
            // ===========Exporting the generated soup============
            File out = new File("./lab02/T1/WSGout/" + output);
            try {
                FileWriter write = new FileWriter(out);
                // Printing the soup to file
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        // debug System.out.print(soup[i][j]);
                        write.write(soup[i][j]);
                    }
                    write.write("\n");
                }
                // Printing the words in the soup to file
                for (String word : words) {
                    write.write(word + ";");
                }
                write.close();
            } catch (IOException ex) {
                System.err.println("ERROR: Cannot create output file.");
            }
        }
    }

    // ===================Function that will build the word soup===========
    public static char[][] wordInsertion(char[][] soup, ArrayList<String> words, int curIdx, int len) {
        // =========Finalization of the soup=========
        if (curIdx == words.size()) { // If all words have been inserted
            Random r = new Random();
            String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Characters we will pull from to fill the empty spaces
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (soup[i][j] == '*') { // If it's an "empty" space
                        soup[i][j] = alpha.charAt(r.nextInt(alpha.length())); // Replace with random character
                    }
                }
            }
        } else {
            // =============Find place to insert word==============
            boolean fits = false;
            String word = words.get(curIdx).toUpperCase();
            List<Integer> iVals = new ArrayList<>();
            List<Integer> jVals = new ArrayList<>();
            // len is size of soup
            for (int i = 0; i < len; i++) {
                iVals.add(i);
                jVals.add(i);
            }
            // Get all spaces in the soup in a random order
            Collections.shuffle(iVals);
            Collections.shuffle(jVals);
            for (int i : iVals) {
                for (int j : jVals) {
                    if (!(soup[i][j] == word.charAt(0) || soup[i][j] == '*'))
                        continue; // space is occupied
                    else {
                        int wordLen = word.length();
                        List<Direction> dirVals = new ArrayList<>();
                        Collections.addAll(dirVals, Direction.values());
                        Collections.shuffle(dirVals);
                        // Randomize the directions to insert the word in
                        for (Direction dir : dirVals) {
                            // Check if the word will fit in desired direction
                            if (dir.getX() > 0 && j + wordLen > len)
                                continue;
                            else if (dir.getX() < 0 && j + 1 < wordLen)
                                continue;
                            else if (dir.getY() > 0 && i + wordLen > len)
                                continue;
                            else if (dir.getY() < 0 && i + 1 < wordLen)
                                continue;
                            else {
                                boolean overlaps = true;
                                fits = true;
                                int searchX = j + dir.getX();// the next x position to look at
                                int searchY = i + dir.getY();// the next y position to look at
                                for (int k = 1; k < wordLen; k++) {
                                    if (soup[searchY][searchX] == '*')
                                        overlaps = false;
                                    if (word.charAt(k) != soup[searchY][searchX] && soup[searchY][searchX] != '*') {
                                        // Place we're at is not empty nor a fitting character
                                        fits = false;
                                        break; // look elsewhere
                                    } else {
                                        // keep looking the the same direction
                                        searchX = searchX + dir.getX();
                                        searchY = searchY + dir.getY();
                                    }
                                }
                                if (fits && !overlaps) {
                                    // Found a place! :D
                                    searchX = j;
                                    searchY = i;
                                    // Now replace in soup with the word
                                    for (int k = 0; k < wordLen; k++) {
                                        soup[searchY][searchX] = word.charAt(k);
                                        searchX = searchX + dir.getX();
                                        searchY = searchY + dir.getY();
                                    }
                                    // run the algorithm again for the next word
                                    char[][] tmpSoup = wordInsertion(soup, words, curIdx + 1, len);
                                    if (tmpSoup[0][0] == '+')
                                        fits = false; // If an error occurred, the first character
                                    // will be a + -> Couldn't fit in all words
                                    else
                                        soup = tmpSoup;
                                } else
                                    fits = false;
                                if (fits)
                                    break; // Only reached if all words fit in -> Solution found!
                            }
                        }
                    }
                    if (fits)
                        break;
                }
                if (fits)
                    break;
            }
            if (!fits) { // Couldn't fit in the word
                soup[0][0] = '+'; // This combination of place+direction doesn't work -> mark as impossible
            }
        }
        return soup;
    }
}
