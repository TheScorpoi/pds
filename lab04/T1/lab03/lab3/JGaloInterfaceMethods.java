package lab3;

public class JGaloInterfaceMethods implements JGaloInterface{
	char [][] tabela;
	char player;
	short counter;
	
	//Construtor
	public JGaloInterfaceMethods(char player) {
		if(Character.toUpperCase(player) == 'X' || Character.toUpperCase(player) == 'O') {
			this.player = player;
			tabela = new char[3][3];
			counter = 0;
		}
		else {
			System.out.println("Player inv�lido! O Player pode ser 'O' ou 'X'!");
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	@Override	
	public char getActualPlayer() {
		// Os jogadores alternam turnos logo � necess�rio um count
		if (counter==0) {
			return this.player;
		}
		else if (this.player == 'O') {
			this.player = 'X';
		}
		else if (this.player == 'X') {
			this.player = 'O';
		}
		return this.player;
	}


	@Override
	public boolean setJogada(int lin, int col) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public char checkResult() {
		// TODO Auto-generated method stub
		return 0;
	}

}
