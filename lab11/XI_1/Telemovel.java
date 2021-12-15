package XI_1;

public class Telemovel {
    
    private String marca;
    private String processador;
    private double preco;
    private double memoria;
    private String camara;

    public Telemovel(String marca, String processador, double preco, double memoria, String camara) {
        this.marca = marca;
        this.processador = processador;
        this.preco = preco;
        this.memoria = memoria;
        this.camara = camara;
    }

    

    public String getMarca() {
        return marca;
    }

    public String getProcessador() {
        return processador;
    }

    public double getPreco() {
        return preco;
    }

    public double getMemoria() {
        return memoria;
    }

    public String getCamara() {
        return camara;
    }

    @Override
    public String toString() {
        return "Telemovel camara=" + camara + ", marca=" + marca + ", memoria=" + memoria + ", preco=" + preco
                + ", processador=" + processador ;
    }
}
