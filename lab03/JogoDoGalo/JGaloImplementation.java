package lab03.JogoDoGalo;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

/**
 * quadra =  MAX_SIZE_QUADRA x MAX_SIZE_QUADRA
 *   __1_____2_____3___
 * 1|     |     |     |               
 *  |_____|_____|_____|               
 * 2|     |     |     |               
 *  |_____|_____|_____|               
 * 3|     |     |     |               
 *  |_____|_____|_____|               
 */
public class JGaloImplementation implements JGaloInterface{
    final static private int MAX_SIZE_QUADRA = 3;
    final static private int MAX_NUMBER_PLAYS = 9;
    
    private char playerA, playerB;
    private int numeroJogadas;
    private char[][] quadra;

    public JGaloImplementation() {
        this.playerA = 'X';
        this.playerB = 'O';
        this.numeroJogadas = 0;
        this.quadra = new char[MAX_SIZE_QUADRA][MAX_SIZE_QUADRA];
        //inicialize the bi-dimentional array all of '1'
        for (int i = 0; i < quadra.length; i++) {
            for (int j = 0; j < quadra.length; j++) {
                quadra[i][j] = '1';
            }
        }
    }

    /** 
    * As by omission, the first player is the playerA (X): playerA will be with the even 
    * numbers (0, 2, 4, 6, 8), and the playerB will be with odd numbers (1, 3, 5, 7, 9)

    * @return the player that will set the play  
    */
    @Override
    public char getActualPlayer() {
        if (numeroJogadas % 2 != 0) {
            return playerB;
        } else {
            return playerA;
        }
    }

    /**
     * Verify if can execute the play, in a true anwser (change the '1' in the quadra, by the 
     * player identifier), lin and col vary between 1 and 3, numeroJogadas increase by one
     * 
     * @param lin: the line in the quadra
     * @param col: the collum in the quadra
     * @return boolean with information if the play can be set or not
     */ 
    @Override
    public boolean setJogada(int lin, int col) {
        //lin and col, have do be decrementaded because the quadra is 0..2, and not 1..3
        lin = lin - 1;
        col = col - 1;
        char player = getActualPlayer();
        if (lin < 3 && lin >= 0 && col < 3 && col >= 0 && this.quadra[lin][col] == '1') {
            this.numeroJogadas++;
            this.quadra[lin][col] = player;
        }
        return false;
    }

    /**
     * Verify if the game finish already, by the numeroJogadas, when its maximum, the result should be a draw
     * and when its bigger or equal to 5, already can exist a winner, for that checkResult only have to be 
     * different of '1'
     * 
     * @return a boolean with information if the game is over or not
     */
    @Override
    public boolean isFinished() {
        if ((this.numeroJogadas == MAX_NUMBER_PLAYS && checkResult() == '1') || (this.numeroJogadas >= 5 && checkResult() != '1')) {
            return true;
        }
        return false;
    }

    /**
     * Verify in all possible diretions: 3 times verticaly, 3 times horizontaly, one time diagonality-right
     * and one time diagonality-left. To reach a winner, have to has 3 positions with the same player identifier
     * 
     * @return a char that identify the winner player
     */
    @Override
    public char checkResult() {
        char[][] quadrado = this.quadra;

        // check in a horizontal way
        for (int i = 0; i < 3; i++) {
            if (quadrado[i][0] != '1' && quadrado[i][0] == quadrado[i][1] && quadrado[i][1] == quadrado[i][2]) {
                return quadrado[i][0];
            }
        }
        // check in a vertical way
        for (int i = 0; i < 3; i++) {
            if (quadrado[0][i] != '1' && quadrado[0][i] == quadrado[1][i] && quadrado[1][i] == quadrado[2][i]) {
                return quadrado[0][i];
            }
        }
        //check in a diagonal-right way
        if (quadrado[2][0] != '1' && quadrado[2][0] == quadrado[1][1] && quadrado[1][1] == quadrado[0][2]) {
            return quadrado[2][0];
        }
        //check in a diagonal-left way
        if (quadrado[0][0] != '1' && quadrado[0][0] == quadrado[1][1] && quadrado[1][1] == quadrado[2][2]) {
            return quadrado[0][0];
        }
        return '1';
    }
}