package aplication;


import xadrez.PecaXadrez;

public class UI {
	
	public static void printBoard(PecaXadrez[][] pecaXadres) {
		for (int i=0; i<pecaXadres.length; i++) {
			System.out.print((8-i) + " ");
			for (int j=0; j<pecaXadres.length; j++) {
			printPiece(pecaXadres[i][j]);	
			}
			System.out.println();
		}
		
		System.out.println("  a b c d e f g h");
		
	}
	
	private static void printPiece(PecaXadrez pecaXadres) {
		if (pecaXadres == null) {
			System.out.print("-");
		}
		else {
			System.out.print(pecaXadres.toString());
		}
		System.out.print(" ");
	}

}
