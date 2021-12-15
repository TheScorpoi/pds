package Command;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

public class Test {

    public static void main(String[] args) {

        //----------------- Tests with String Array
        System.out.println("\nTests with Strings\n");
        List<String> testList = new ArrayList<>();

        CommandAdd<String> add = new CommandAdd<String>(testList);
        CommandRemove<String> remove = new CommandRemove<String>(testList);

        add.execute("Sol");
        add.execute("Chuva");
        add.execute("Nublado");
        add.execute("Neve");
        add.execute("Geada");
        add.execute("Granizo");

        System.out.println("After execute adds:   " + testList);

        add.undo();
        add.undo();
        add.undo();

        System.out.println("After execute undo:   " + testList);

        remove.execute("Sol");
        remove.execute("Chuva");
        
        System.out.println("After execute removes:" + testList);

        remove.undo();
        remove.undo();

        System.out.println("After execute undo:   " + testList);

        //----------------- Tests with int Array
        System.out.println("\nTests with Integers\n");

        List<Integer> integerList = new ArrayList<>();

        CommandAdd<Integer> addInteger = new CommandAdd<Integer>(integerList);
        CommandRemove<Integer> removeInteger = new CommandRemove<Integer>(integerList);

        addInteger.execute(1);
        addInteger.execute(2);
        addInteger.execute(3);
        addInteger.execute(4);
        addInteger.execute(5);
        addInteger.execute(6);

        System.out.println("After execute adds:   " + integerList);

        addInteger.undo();
        addInteger.undo();
        addInteger.undo();

        System.out.println("After execute undo:   " + integerList);

        removeInteger.execute(1);
        removeInteger.execute(2);
        
        System.out.println("After execute removes:" + integerList);

        removeInteger.undo();

        System.out.println("After execute undo:   " + integerList);

        System.out.println("\nTests with a SortedSet\n");

        SortedSet<String> sortedSet = new TreeSet<String>();

        CommandAdd<String> addsorted = new CommandAdd<String>(sortedSet);
        CommandRemove<String> removesorted = new CommandRemove<String>(sortedSet);
        
        addsorted.execute("D");
        addsorted.execute("F");
        addsorted.execute("C");
        addsorted.execute("E");
        addsorted.execute("B");
        addsorted.execute("A");

        System.out.println("After execute adds:   " + sortedSet);
        
        addsorted.undo();
        addsorted.undo();
        addsorted.undo();

        System.out.println("After execute undo:   " + sortedSet);

        removesorted.execute("D");
        removesorted.execute("F");

        System.out.println("After execute removes:" + sortedSet);

        removesorted.undo();
        removesorted.undo();

        System.out.println("After execute undo:   " + sortedSet);

        System.out.println();
    }

}
