package lab01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WSSolver {
    public static void main(String[] args) {

        List<String> puzzle = new ArrayList<>();
        List<String> listaPalavras = new ArrayList<>();
        final int max = 40;
        char[][] sopa = new char[max][max];

        try (Scanner sc = new Scanner(new File(args[0]))) {
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                if (linha.length() <= max && upper(linha)) {
                    puzzle.add(linha); // adicionar cada linha do ficheiro à lista puzzle
                } else if (!upper(linha)) {
                    String[] linhaComPalavras = linha.split(";|\\,|\\ ");
                    for (int i = 0; i < linhaComPalavras.length; i++) {
                        listaPalavras.add(linhaComPalavras[i].toUpperCase()); // adicionar à listaPalavras as palavras a encontrar
                    }
                }
            }
            if (!checkSquare(puzzle)) { // verificar se o puzzle é quandrado
                System.err.println("Puzzle with a ilegal size, should be in maximum 40x40");
                System.exit(1);
            }

            if(findDuplicates(listaPalavras)) {
                System.err.println("ERRO: Palavras duplicadas encontradas");
                System.exit(1);
            }
    
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        for (int i = 0; i < puzzle.size(); i++) { // preencher array bidimencional, cada [][] = uma letra de puzzle
            sopa[i] = puzzle.get(i).toCharArray();
        }

        solverFunction(sopa, listaPalavras);
    }

    public static void solverFunction(char[][] sopaLetras, List<String> listaPalavras) {
        try (PrintWriter out = new PrintWriter(new File("./Ficheiros/out3.txt"))) {
            for (String palavra : listaPalavras) { // percorrer todas as palavras da listaPlavras
                char primeiraLetra = palavra.charAt(0);
                for (int linha = 0; linha < sopaLetras.length; linha++) {
                    for (int coluna = 0; coluna < sopaLetras[linha].length; coluna++) { // percorrer todas as letras da sopaLetras
                        if (primeiraLetra == sopaLetras[linha][coluna]) { // se a primeira letra == a letra da sopa, testamos as 8 possibilidades de direção à sua volta
                            if (checkDirections(sopaLetras, palavra, linha, coluna, 0, 1)) {
                                out.printf("%-15s\t%3d\t%2d,%2d\t%10s\n", palavra, palavra.length(), linha + 1, coluna + 1, "Right");
                                continue; // os if todos tem o continue pq assim caso entrem num escusam de ir testar os de baixo
                            }
                            if (checkDirections(sopaLetras, palavra, linha, coluna, 0, -1)) {
                                out.printf("%-15s\t%3d\t%2d,%2d\t%10s\n", palavra, palavra.length(), linha + 1, coluna + 1, "Left");
                                continue;
                            }
                            if (checkDirections(sopaLetras, palavra, linha, coluna, 1, 0)) {
                                out.printf("%-15s\t%3d\t%2d,%2d\t%10s\n", palavra, palavra.length(), linha + 1, coluna + 1, "Down");
                                continue;
                            }
                            if (checkDirections(sopaLetras, palavra, linha, coluna, -1, 0)) {
                                out.printf("%-15s\t%3d\t%2d,%2d\t%10s\n", palavra, palavra.length(), linha + 1, coluna + 1, "Up");
                                continue;
                            }
                            if (checkDirections(sopaLetras, palavra, linha, coluna, 1, 1)) {
                                out.printf("%-15s\t%3d\t%2d,%2d\t%10s\n", palavra, palavra.length(), linha + 1, coluna + 1, "DownRight");
                                continue;
                            }
                            if (checkDirections(sopaLetras, palavra, linha, coluna, 1, -1)) {
                                out.printf("%-15s\t%3d\t%2d,%2d\t%10s\n", palavra, palavra.length(), linha + 1, coluna + 1, "DownLeft");
                                continue;
                            }
                            if (checkDirections(sopaLetras, palavra, linha, coluna, -1, 1)) {
                                out.printf("%-15s\t%3d\t%2d,%2d\t%10s\n", palavra, palavra.length(), linha + 1, coluna + 1, "UpRight");
                                continue;
                            }
                            if (checkDirections(sopaLetras, palavra, linha, coluna, -1, -1)) {
                                out.printf("%-15s\t%3d\t%2d,%2d\t%10s\n", palavra, palavra.length(), linha + 1, coluna + 1, "UpLeft");
                                continue;
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean checkDirections(char[][] sopa, String palavra, int linha, int coluna, int linhaIncremnto,
            int colunaIncremento) {
        for (int i = 0; i < palavra.length(); i++) {
            // condicoes para que não seja a direcao certo: sair fora do "puzzle" ou a letra
            // na sopa nao ser a mesma da palavra
            if (linha < 0 || coluna < 0 || linha >= sopa.length || coluna >= sopa[linha].length || sopa[linha][coluna] != palavra.charAt(i)) {
                return false;
            }
            linha += linhaIncremnto;
            coluna += colunaIncremento;
        }
        return true;
    }

    public static boolean upper(String palavra) { // verifica se as letras estão em maisculo
        for (int i = 0; i < palavra.length(); i++) {
            if (!Character.isUpperCase(palavra.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkSquare(List<String> array) { // verifica se o puzzle é quadrado
        if (array.get(0).length() != array.size()) {
            return false;
        }
        return true;
    }

    public static boolean findDuplicates(List<String> words) {
        for (int k = 0; k < words.size(); k++) {
            for (int j = k + 1; j < words.size(); j++) {
                if (words.get(k).contains(words.get(j)) || words.get(j).contains(words.get(k))) {
                    return true;
                }
            }
        }
        return false;
    } 
}