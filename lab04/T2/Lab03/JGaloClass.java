package Lab03;

import java.util.*;
public class JGaloClass implements JGaloInterface {
   
    private char player1;
    private char player2;
    private int currentPlayer;
    private char[][] board;
    private int moves;
    
   
    public JGaloClass(int playerstart) {
        this.player1 = 'X';
        this.player2 = 'O';
        this.currentPlayer = playerstart;
        this.board = new char[3][3];
        this.moves = 0;
        
    }

  
    public char getActualPlayer() {
        if (this.currentPlayer % 2 == 1)
            return this.player1;
        else {
        	return this.player2;
    
        }
    }
	public boolean setJogada(int lin, int col) {
		lin--;
		col--;
		if (lin >= 0 || lin < 3 || col >= 0 || col < 3 ||this.board[lin][col] != '\0') {
			char player = getActualPlayer();
			this.board[lin][col] = player;
			this.currentPlayer++;
			this.moves++;
			
			return true;
		}
		else {
			return false;
		}
	}

	public  boolean isFinished() {
		if (checkResult() != ' ' || moves == 9) {
			return true;
		}
		else {
			return false;
		}
		
	}
    
	
	public char checkResult() {
		char[] vict1 = this.board[0]; /* vict1 a vict8 sao todas as linahs do jogo onde se pode ganhar*/
		char[] vict2 = this.board[1];
		char[] vict3 = this.board[2];
		char[] vict4 = new char[3];
		char[] vict5 = new char[3];
		char[] vict6 = new char[3];
		
		for (int i= 0; i < 3 ;  i++) {
			vict4[i] = this.board[i][0];
			vict5[i] = this.board[i][1];
			vict6[i] = this.board[i][2];
		}
		char[] vict7 = new char[] {this.board[0][0],this.board[1][1],this.board[2][2]};
		char[] vict8 = new char[] {this.board[0][2],this.board[1][1],this.board[2][0]};
		
		List<char[]> victPoss = new ArrayList<char[]>();
		//--------------------------------------------
		// adicionar a lista de vitorias possiveis
		victPoss.add(vict1);victPoss.add(vict2);victPoss.add(vict3);
		victPoss.add(vict4);victPoss.add(vict5);victPoss.add(vict6);
		victPoss.add(vict7);victPoss.add(vict8);
		//--------------------------------------------
		
		for (char[] vict : victPoss) {
			if (vict[0] == player1 && vict[1] == player1 && vict[2] == player1  ) { //verificar se algumas das linhas
				return player1;                                                     //se pode ganhar tem os memso caracters
				
			}
			else if (vict[0] == player2 && vict[1] == player2 && vict[2] == player2  ) {
				return player2;
				
			}
			
		}
		
		return ' ';
		
	}
    
} 
    
    
    
    
    