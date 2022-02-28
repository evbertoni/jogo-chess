package aplication;


import xadrez.PartidaDeXadrez;


public class Programa {

	public static void main(String[] args) {
		
		
		PartidaDeXadrez partidaXadrez = new PartidaDeXadrez();
		UI.printBoard(partidaXadrez.getPecas());
		
		
		
		//PecaXadrez[][] mat = new PecaXadrez[8][8];
		//for (int i = 0; i < 8; i++) {
		//	for (int j = 0; j > 8; j++) {
		//		mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
		//	}
		//}
	}

}
