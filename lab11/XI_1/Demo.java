package XI_1;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */


/**
 * a) O melhor padrão para implementar uma resolucao para este exercicio é o "Strategy"
 * b) na pasta Diagrams
 * c) Algoritmos de ordenacao usados: Bubble Sort, Insertion Sort, Selection Sort (na tabela os tempos estão em milisegundos)
 */

//Pattern Used: Strategy
public class Demo {

    public static void main(String[] args) {

        double start, stop, deltaBubbleMarca, deltaBubblePreco, deltaBubbleMemoria, deltaInsertionMemoria, deltaInsertionMarca,
                deltaInsertionPreco, deltaSelectionMarca, deltaSelectionMemoria, deltaSelectionPreco;
        
            Telemovel[] telemovelList = { new Telemovel("SAMSUNG", "I8", 356.12, 8.0, "2 CAMARAS"),
                    new Telemovel("HUAWEI", "X4", 534.12, 4.0, "3 CAMARAS"),
                    new Telemovel("APPLE", "A13", 612.45, 6.0, "4 CAMARAS"),
                    new Telemovel("APPLE", "A14", 742.98, 8.0, "1 CAMARAS"),
                    new Telemovel("APPLE", "A13", 866.89, 6.0, "2 CAMARAS"),
                    new Telemovel("HUAWEI", "GYT8", 334.75, 12.0, "4 CAMARAS"),
                    new Telemovel("SAMSUNG", "I7", 336.27, 6.0, "1 CAMARAS"),
                    new Telemovel("NOKIA", "X13", 765.23, 4.0, "2 CAMARAS"),
                    new Telemovel("XIAOMI", "LL8", 123.00, 10.0, "3 CAMARAS") };

            //-----BUBBLE-SORT--------
            start = System.nanoTime();
            ContextSorting bubble = new ContextSorting(new BubbleSort());
            bubble.sort(telemovelList, "preco");
            stop = System.nanoTime();
            deltaBubblePreco = (stop - start) / 1e6;

            start = System.nanoTime();
            bubble.sort(telemovelList, "memoria");
            stop = System.nanoTime();
            deltaBubbleMemoria = (stop - start) / 1e6;

            start = System.nanoTime();
            bubble.sort(telemovelList, "marca");
            stop = System.nanoTime();
            deltaBubbleMarca = (stop - start) / 1e6;
        
        //-----MERGE-SORT-------
            start = System.nanoTime();
            ContextSorting insertion = new ContextSorting(new InsertionSort());
            insertion.sort(telemovelList, "preco");
            stop = System.nanoTime();
            deltaInsertionPreco = (stop - start) / 1e6;

            start = System.nanoTime();
            insertion.sort(telemovelList, "memoria");
            stop = System.nanoTime();
            deltaInsertionMemoria = (stop - start) / 1e6;

            start = System.nanoTime();
            insertion.sort(telemovelList, "marca");
            stop = System.nanoTime();
            deltaInsertionMarca = (stop - start) / 1e6;
        
        
        //------SELECTION-SORT----------
            start = System.nanoTime();
            ContextSorting selection = new ContextSorting(new SelectionSort());
            selection.sort(telemovelList, "preco");
            stop = System.nanoTime();
            deltaSelectionPreco = (stop - start) / 1e6;

            start = System.nanoTime();
            selection.sort(telemovelList, "memoria");
            stop = System.nanoTime();
            deltaSelectionMemoria = (stop - start) / 1e6;

            start = System.nanoTime();
            selection.sort(telemovelList, "marca");
            stop = System.nanoTime();
            deltaSelectionMarca = (stop - start) / 1e6;
        

        System.out.printf("SORT METHOD\t\tPRICE\tMEMORY\tBRAND\n");
        System.out.printf("Bubble Sort %16.2f %7.2f %7.2f\n", deltaBubblePreco, deltaBubbleMemoria, deltaBubbleMarca);
        System.out.printf("Insertion Sort %13.2f %7.2f %7.2f\n", deltaInsertionPreco, deltaInsertionMemoria, deltaInsertionMarca);
        System.out.printf("Selection Sort %13.2f %7.2f %7.2f\n", deltaSelectionPreco, deltaSelectionMemoria, deltaSelectionMarca);

    }
}
