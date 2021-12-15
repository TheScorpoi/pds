package XI_1;

public class ContextSorting {
    
    private SortStrategy strategy;

    public ContextSorting (SortStrategy strategy){
        this.strategy = strategy;
    }

    public void sort(Telemovel[] input, String atribute) {
        strategy.sort(input, atribute);
    }
}
