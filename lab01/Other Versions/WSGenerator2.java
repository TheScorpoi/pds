package lab01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WSGenerator2 {

    private static ArrayList<String> outPut = new ArrayList<String>();
    static String fileNameInput, fileNameOutput;
    static int size;

    public static void main(String[] args) {

        if (args.length == 6 && args[0].equals("-i") && args[2].equals("-s") && args[4].equals("-o")) {
            fileNameInput = args[1];
            size = Integer.parseInt(args[3]);
            if (size > 40) {System.err.println("ERRO: SIZE tem de ser um numero < 40"); System.exit(1);}
            fileNameOutput = args[5];
        } else if (args.length == 4 && args[0].equals("-i") && args[2].equals("-s")) {
            fileNameInput = args[1];
            size = Integer.parseInt(args[3]);
            if (size > 40) {System.err.println("ERRO: SIZE tem de ser um numero < 40"); System.exit(1);}
        } else {
            System.err.println("ERRO! Argumentos passados incorretamente!");
            System.exit(1);
        }

        List<String> wordsList = lerFicheiro(fileNameInput, size);
        char[][] sopaInicial = iniciarSopa(size);
        char[][] sopaPreenchidaComPontos = preencherSopa(sopaInicial, wordsList, size);

        char[][] sopaFinal = sopaFinal(sopaPreenchidaComPontos, size);
        
        if (args.length == 4) {
            for (int i = 0; i < sopaFinal.length; i++) {
                for (int j = 0; j < sopaFinal[i].length; j++) {
                    System.out.print(sopaFinal[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            for (String word : wordsList) {
                System.out.printf("%s ", word);
            }
            System.out.println();
        } else if (args.length == 6) {
            imprimirResultados(sopaPreenchidaComPontos, sopaFinal, outPut, fileNameOutput, wordsList);
        }
    }

    // Funcao para ler o ficheiro que contem as palavras a formar a sopa de letras
    public static List<String> lerFicheiro(String fileName, int size) {
        List<String> lista = new ArrayList<>();
        try (Scanner input = new Scanner(new File("./Ficheiros/" + fileName))) {
            while (input.hasNext()) {
                String[] tab = input.nextLine().split("[; , ]");
                for (int i = 0; i < tab.length; i++) {
                    if (tab[i].length() > size) { //CONDICAO PARA VERIFICAR SE PALAVRAS CABEM DENTRO DO PUZZLE
                        System.err.println("ERRO: Tamanho do puzzle incompativel com as palavras");
                        System.exit(1);
                    } else {
                        lista.add(tab[i].toUpperCase());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        if (findDuplicates(lista)) {
            System.err.println("ERRO: Palavras duplicadas encontradas");
            System.exit(1);
        }
        return lista;
    }

    // Funcao que imprime a sopa de letras na versao final (palavras e letras random)
    public static char[][] sopaFinal(char[][] sopaF, int size) {
        char[][] sopa = new char[size][size];
        for (int i = 0; i < sopaF.length; i++) {//copia de sopaF para sopa
            for (int j = 0; j < sopaF.length; j++) {
                sopa[i][j] = sopaF[i][j];
            }
        }
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                if (sopa[i][j] == '.') {
                    sopa[i][j] = (char)(Math.random() * (90 - 65) + 65);//int ASCII para as letras do alfabeto em maisculo
                }
            }
        }
        return sopa;
    }
    
    // Funcao para imprimir quer no terminal, quer no ficheiro de output
    public static void imprimirResultados(char[][] sopaPreenchida, char[][] sopaFinal,List<String> outPut, String fileName, List<String> wordsList) {
        for (String line : outPut) { //prints para o terminal com solucao
            System.out.println(line);
        }
        System.out.println();
        for (int i = 0; i < sopaPreenchida.length; i++) {
            for (int j = 0; j < sopaPreenchida[i].length; j++) {
                System.out.printf("%c ", sopaPreenchida[i][j]);
            }
            System.out.println();
        }
        //prints para o ficheiro
        try (PrintWriter out = new PrintWriter(new File("./Ficheiros/" + fileName))) {
            for (int i = 0; i < sopaFinal.length; i++) {
                for (int j = 0; j < sopaFinal[i].length; j++) {
                    out.printf("%c", sopaFinal[i][j]);
                }
                out.println();
            }
            for (String word : wordsList) {
                out.printf("%s ", word);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    // Funcao que inicializa cada indice do array bidimensional com o char '.'
    public static char[][] iniciarSopa(int size) {
        char[][] sopa = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sopa[i][j] = (char) ('.');
            }
        }
        return sopa;
    }

    // Funcao que verifica se ha disponibilidade de a palavra ser inserida na sopa, sem que ocorra overwrite em outra palavras ja inseridas
    public static boolean verificaDisponibildiade(char[][] sopa, String word, int linha, int coluna, int incLinha, int incColuna) {
        for (int i = 0; i < word.length(); i++) {
            if (linha < 0 || coluna < 0 || linha >= sopa.length || coluna >= sopa[linha].length || sopa[linha][coluna] != '.') {
                return false;
            }
            linha += incLinha;
            coluna += incColuna;
        }
        return true;
    }

    // Funcao que vai preenchendo a sopa de acordo com a verificaDisponibilidade e de forma random(up, down, right ...)
    //*AVISO: a parte do while em cada case, está um bocado martelada but it works and its quick, so... i dont think that efficiency is the most important here
    public static char[][] preencherSopa(char[][] sopa, List<String> words, int size) {
        boolean flag;
        for (String word : words) {
            switch ((int) (Math.random() * (8 - 1) + 1)) { // vezes 8 porque temos 8 possibilidades: up, down, right ...
            case (1):// up
                int minLinhaCima = word.length() - 1;
                int maxLinhaCima = size - 1;
                int linhaCima = (int) (Math.random() * (maxLinhaCima - minLinhaCima)) + minLinhaCima;
                int colunaCima = (int) (Math.random() * (size));
                int tmp_linhaCima = linhaCima;
                int tmp_colunaCima = colunaCima;
                flag = true;
                while (flag) {
                    if (verificaDisponibildiade(sopa, word, linhaCima, colunaCima, -1, 0)) {
                        for (int i = 0; i < word.length(); i++) {
                            sopa[linhaCima][colunaCima] = word.toUpperCase().charAt(i);
                            linhaCima--;
                        }
                        outPut.add(String.format("%-10s\t%2d,%2d\t%10s", word, (tmp_linhaCima + 1), (tmp_colunaCima + 1), "UP"));
                        flag = false;
                    } else {
                    linhaCima = (int) (Math.random() * (maxLinhaCima - minLinhaCima)) + minLinhaCima;
                    colunaCima = (int) (Math.random() * (size));
                    tmp_linhaCima = linhaCima;
                    tmp_colunaCima = colunaCima;
                    }
                }
                break;
            case (2): // Down
                int maxLinhaBaixo = word.length() - 1;
                int linhaBaixo = (int) (Math.random() * (maxLinhaBaixo));
                int colunaBaixo = (int) (Math.random() * (size));
                int tmp_linhaBaixo = linhaBaixo;
                int tmp_colunaBaixo = colunaBaixo;
                flag = true;
                while (flag) {
                    if (verificaDisponibildiade(sopa, word, linhaBaixo, colunaBaixo, 1, 0)) {
                        for (int i = 0; i < word.length(); i++) {
                            sopa[linhaBaixo][colunaBaixo] = word.toUpperCase().charAt(i);
                            linhaBaixo++;
                        }
                        outPut.add(String.format("%-10s\t%2d,%2d\t%10s", word, (tmp_linhaBaixo + 1), (tmp_colunaBaixo + 1), "Down"));
                        flag = false;
                    } else {
                        linhaBaixo = (int) (Math.random() * (maxLinhaBaixo));
                        colunaBaixo = (int) (Math.random() * (size));
                        tmp_linhaBaixo = linhaBaixo;
                        tmp_colunaBaixo = colunaBaixo;
                    }
                }
                break;
            case (3):// left
                int minColunaEsquerda = word.length() - 1;
                int maxColunaEsquerda = size - 1;
                int linhaEsquerda = (int) (Math.random() * (size));
                int colunaEsquerda = (int) (Math.random() * (maxColunaEsquerda - minColunaEsquerda)) + minColunaEsquerda;
                int tmp_linhaEsquerda = linhaEsquerda;
                int tmp_colunaEsquerda = colunaEsquerda;
                flag = true;
                while (flag) {
                    if (verificaDisponibildiade(sopa, word, linhaEsquerda, colunaEsquerda, 0, -1)) {
                        for (int i = 0; i < word.length(); i++) {
                            sopa[linhaEsquerda][colunaEsquerda] = word.toUpperCase().charAt(i);
                            colunaEsquerda--;
                        }
                        outPut.add(String.format("%-10s\t%2d,%2d\t%10s", word, (tmp_linhaEsquerda + 1),
                                (tmp_colunaEsquerda + 1), "Left"));
                        flag = false;
                    } else {
                        linhaEsquerda = (int) (Math.random() * (size));
                        colunaEsquerda = (int) (Math.random() * (maxColunaEsquerda - minColunaEsquerda));
                        tmp_linhaEsquerda = linhaEsquerda;
                        tmp_colunaEsquerda = colunaEsquerda;
                    }
                }
                break;
            case (4):// Right
                int maxColunaDireita = size - word.length();
                int linhaDireita = (int) (Math.random() * (size));
                int colunaDireita = (int) (Math.random() * (maxColunaDireita));
                int tmp_linha = linhaDireita;
                int tmp_coluna = colunaDireita;
                flag = true;
                while (flag) {
                    if (verificaDisponibildiade(sopa, word, linhaDireita, colunaDireita, 0, 1)) {
                        for (int i = 0; i < word.length(); i++) {
                            sopa[linhaDireita][colunaDireita] = word.toUpperCase().charAt(i);
                            colunaDireita++;
                        }
                        outPut.add(
                                String.format("%-10s\t%2d,%2d\t%10s", word, (tmp_linha + 1), (tmp_coluna + 1), "Right"));
                        flag = false;
                    } else {
                        linhaDireita = (int) (Math.random() * (size));
                        colunaDireita = (int) (Math.random() * (maxColunaDireita));
                        tmp_linha = linhaDireita;
                        tmp_coluna = colunaDireita;
                    }
                }
                break;
            case (5):// up-left
                int minColunaUL = word.length() - 1;
                int maxColunaUL = size - word.length() - 1;
                int minLinhaUL = word.length() - 1;
                int maxLinhaUL = size - word.length() - 1;
                int linhaUL = (int) (Math.random() * (maxLinhaUL - minLinhaUL)) + minLinhaUL;
                int colunaUL = (int) (Math.random() * (maxColunaUL - minColunaUL)) + minColunaUL;
                int tmp_linhaUL = linhaUL;
                int tmp_colunaUL = colunaUL;
                flag = true;
                while (flag) {
                    if (verificaDisponibildiade(sopa, word, linhaUL, colunaUL, -1, -1)) {
                        for (int i = 0; i < word.length(); i++) {
                            sopa[linhaUL][colunaUL] = word.toUpperCase().charAt(i);
                            linhaUL--;
                            colunaUL--;
                        }
                        outPut.add(String.format("%-10s\t%2d,%2d\t%10s", word, (tmp_linhaUL + 1), (tmp_colunaUL + 1), "Up-left"));
                        flag = false;
                    } else {
                        linhaUL = (int) (Math.random() * (maxLinhaUL - minLinhaUL)) + minLinhaUL;
                        colunaUL = (int) (Math.random() * (maxColunaUL - minColunaUL)) + minColunaUL;
                        tmp_linhaUL = linhaUL;
                        tmp_colunaUL = colunaUL;
                    }
                }
                break;
            case (6):// up-right
                int minColunaUR = 0;
                int maxColunaUR = size - word.length();
                int minLinhaUR = word.length() - 1;
                int maxLinhaUR = size;
                int linhaUR = (int) (Math.random() * (maxLinhaUR - minLinhaUR)) + minLinhaUR;
                int colunaUR = (int) (Math.random() * (maxColunaUR - minColunaUR)) + minColunaUR;
                int tmp_linhaUR = linhaUR;
                int tmp_colunaUR = colunaUR;
                flag = true;
                while (flag) {
                    if (verificaDisponibildiade(sopa, word, linhaUR, colunaUR, -1, 1)) {
                        for (int i = 0; i < word.length(); i++) {
                            sopa[linhaUR][colunaUR] = word.toUpperCase().charAt(i);
                            linhaUR--;
                            colunaUR++;
                        }
                        outPut.add(String.format("%-10s\t%2d,%2d\t%10s", word, (tmp_linhaUR + 1), (tmp_colunaUR + 1), "Up-right"));
                        flag = false;
                    } else {
                        linhaUR = (int) (Math.random() * (maxLinhaUR - minLinhaUR)) + minLinhaUR;
                        colunaUR = (int) (Math.random() * (maxColunaUR - minColunaUR)) + minColunaUR;
                        tmp_linhaUR = linhaUR;
                        tmp_colunaUR = colunaUR;
                    }
                }
                break;
            case (7): // down-left
                int minColunaDL = word.length() - 1;
                int maxColunaDL = size;
                int minLinhaDL = 0;
                int maxLinhaDL = size - word.length();
                int linhaDL = (int) (Math.random() * (maxLinhaDL - minLinhaDL)) + minLinhaDL;
                int colunaDL = (int) (Math.random() * (maxColunaDL - minColunaDL)) + minColunaDL;
                int tmp_linhaDL = linhaDL;
                int tmp_colunaDL = colunaDL;
                flag = true;
                while (flag) {
                    if (verificaDisponibildiade(sopa, word, linhaDL, colunaDL, 1, -1)) {
                        for (int i = 0; i < word.length(); i++) {
                            sopa[linhaDL][colunaDL] = word.toUpperCase().charAt(i);
                            linhaDL++;
                            colunaDL--;
                        }
                        outPut.add(String.format("%-10s\t%2d,%2d\t%10s", word, (tmp_linhaDL + 1), (tmp_colunaDL + 1), "Down-left"));
                        flag = false;
                    } else {
                        linhaDL = (int) (Math.random() * (maxLinhaDL - minLinhaDL)) + minLinhaDL;
                        colunaDL = (int) (Math.random() * (maxColunaDL - minColunaDL)) + minColunaDL;
                        tmp_linhaDL = linhaDL;
                        tmp_colunaDL = colunaDL;
                    }
                }
                break;
            case (8):// down-right
                int minLinhaDR = 0;
                int maxLinhaDR = size - word.length();
                int minColunaDR = 0;
                int maxColunaDR = size - word.length();
                int linhaDR = (int) (Math.random() * (maxLinhaDR - minLinhaDR)) + minLinhaDR;
                int colunaDR = (int) (Math.random() * (maxColunaDR - minColunaDR)) + minColunaDR;
                int tmp_linhaDR = linhaDR;
                int tmp_colunaDR = colunaDR;
                flag = true;
                while (flag) {
                    if (verificaDisponibildiade(sopa, word, linhaDR, colunaDR, 1, 1)) {
                        for (int i = 0; i < word.length(); i++) {
                            sopa[linhaDR][colunaDR] = word.toUpperCase().charAt(i);
                            linhaDR++;
                            colunaDR++;
                        }
                        outPut.add(String.format("%-10s\t%2d,%2d\t%10s", word, (tmp_linhaDR + 1), (tmp_colunaDR + 1), "Down-right"));
                        flag = false;
                    } else {
                        linhaDR = (int) (Math.random() * (maxLinhaDR - minLinhaDR)) + minLinhaDR;
                        colunaDR = (int) (Math.random() * (maxColunaDR - minColunaDR)) + minColunaDR;
                        tmp_linhaDR = linhaDR;
                        tmp_colunaDR = colunaDR;
                    }
                }
                break;
            }
        }
        return sopa;
    }

    // Funcao que verifica se existem palavras que se encontram dentro de outras
    public static boolean findDuplicates(List<String> words) {
        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                if (words.get(i).contains(words.get(j)) || words.get(j).contains(words.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}