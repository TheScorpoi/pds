package XI_1;

public class InsertionSort implements SortStrategy{
    
    @Override
    public Telemovel[] sort(Telemovel[] arr, String attribute)
    {
        int n = arr.length;

        switch (attribute){
            case "preco":
                for (int i = 1; i < n; ++i) {
                    Telemovel key = arr[i];
                    int j = i - 1;
        
                    /* Move elements of arr[0..i-1], that are
                    greater than key, to one position ahead
                    of their current position */
                    while (j >= 0 && arr[j].getPreco() > key.getPreco()) {
                        arr[j + 1] = arr[j];
                        j = j - 1;
                    }
                    arr[j + 1] = key;
                }
                break;
            case "memoria":
                for (int i = 1; i < n; ++i) {
                    Telemovel key = arr[i];
                    int j = i - 1;
        
                    /* Move elements of arr[0..i-1], that are
                    greater than key, to one position ahead
                    of their current position */
                    while (j >= 0 && arr[j].getMemoria() > key.getMemoria()) {
                        arr[j + 1] = arr[j];
                        j = j - 1;
                    }
                    arr[j + 1] = key;
                }
            break;
            case "marca":
                for (int i = 1; i < n; ++i) {
                    Telemovel key = arr[i];
                    int j = i - 1;
        
                    /* Move elements of arr[0..i-1], that are
                    greater than key, to one position ahead
                    of their current position */
                    while (j >= 0 && arr[j].getMarca().compareTo(key.getMarca())>0) {
                        arr[j + 1] = arr[j];
                        j = j - 1;
                    }
                    arr[j + 1] = key;
                }
            break;
        }

        return arr;
    }

    
}
