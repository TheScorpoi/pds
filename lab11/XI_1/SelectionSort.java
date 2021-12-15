package XI_1;
//https://www.geeksforgeeks.org/selection-sort/
public class SelectionSort implements SortStrategy{

    @Override
    public Telemovel[] sort(Telemovel[] arr, String attribute) {

        int n = arr.length;
 
        switch (attribute){
            case "preco":
                for (int i = 0; i < n-1; i++)
                {
                    int min_idx = i;
                    for (int j = i+1; j < n; j++)
                        if (arr[j].getPreco() < arr[min_idx].getPreco())
                            min_idx = j;
        
                    Telemovel temp = arr[min_idx];
                    arr[min_idx] = arr[i];
                    arr[i] = temp;
                }
                break;
            case "memoria":
                for (int i = 0; i < n-1; i++)
                {
                    int min_idx = i;
                    for (int j = i+1; j < n; j++)
                        if (arr[j].getMemoria() < arr[min_idx].getMemoria())
                            min_idx = j;
        
                    Telemovel temp = arr[min_idx];
                    arr[min_idx] = arr[i];
                    arr[i] = temp;
                }
                break;
            case "marca":
                for (int i = 0; i < n-1; i++)
                {
                    int min_idx = i;
                    for (int j = i+1; j < n; j++)
                        if (arr[i].getMarca().compareTo(arr[j].getMarca()) > 0)
                            min_idx = j;
        
                    Telemovel temp = arr[min_idx];
                    arr[min_idx] = arr[i];
                    arr[i] = temp;
                }
                break;
            default:
                return null;

        }
        return arr;   
    }
    

}
