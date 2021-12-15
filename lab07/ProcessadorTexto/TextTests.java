package ProcessadorTexto;

import java.io.File;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

//Pattern used: Decorator

public class TextTests {

    public static void main(String[] args) {
        
        System.out.println("*********************************************\n***************TEXT*PROCESSING***************\n*********************************************\n");
        File file = new File("./file.txt");

        //Some combinations of tests
        System.out.println("\n** Some TESTS");
        CapitalizationFilter capitalized = new CapitalizationFilter(new VowelFilter(new TermFilter(new TextReader(file))));
        while (capitalized.hasNext()) {
            System.out.println(capitalized.next());
        }
    
        //Normalization Test
        System.out.println("\n** NormalizationFilter TEST");
        NormalizationFilter normalFilter1 = new NormalizationFilter(new TextReader(file));
        while (normalFilter1.hasNext()) {
            System.out.println(normalFilter1.next());
        }

        //vowels test
        System.out.println("\n** VowelFilter TEST");
        VowelFilter vowelFilter1 = new VowelFilter(new TextReader(file));
        while (vowelFilter1.hasNext()) {
            System.out.println(vowelFilter1.next());
        }
        
        //Tests with all the filters
        System.out.println("\n** All TESTS");
        TermFilter termF = new TermFilter(new VowelFilter(new NormalizationFilter(new CapitalizationFilter(new TextReader(file))))); 
        while (termF.hasNext()) {
            System.out.println(termF.next());
        }

        System.out.println("\n*********************************************\n*********************************************\n*********************************************");
    }

}
