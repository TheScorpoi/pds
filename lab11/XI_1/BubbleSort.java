package XI_1;

//https://www.geeksforgeeks.org/bubble-sort/
public class BubbleSort implements SortStrategy {

    @Override
    public Telemovel[] sort(Telemovel[] telemoveis, String atribute) {

        switch (atribute) {

            case "preco":
                for (int i = 0; i < telemoveis.length - 1; i++) {
                    for (int j = 0; j < telemoveis.length - i - 1; j++) {
                        if (telemoveis[j].getPreco() > telemoveis[j + 1].getPreco()) {
                            Telemovel temp = telemoveis[j];
                            telemoveis[j] = telemoveis[j + 1];
                            telemoveis[j + 1] = temp;
                        }
                    }

                }

                return telemoveis;

            case "memoria":
                for (int i = 0; i < telemoveis.length - 1; i++) {
                    for (int j = 0; j < telemoveis.length - i - 1; j++) {
                        if (telemoveis[j].getMemoria() > telemoveis[j + 1].getMemoria()) {
                            Telemovel temp = telemoveis[j];
                            telemoveis[j] = telemoveis[j + 1];
                            telemoveis[j + 1] = temp;
                        }
                    }

                }
                return telemoveis;
            
            case "marca":
                for (int i = 0; i < telemoveis.length - 1; i++) {
                    for (int j = 0; j < telemoveis.length - i - 1; j++) {
                        if (telemoveis[j].getMarca().compareTo(telemoveis[j + 1].getMarca()) > 0) {
                            Telemovel temp = telemoveis[j];
                            telemoveis[j] = telemoveis[j + 1];
                            telemoveis[j + 1] = temp;
                        }
                    }

                }
                return telemoveis;
                

            default:
                return null;
        }
    }
}
