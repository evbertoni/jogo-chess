package aplication;


import xadres.PecaXadres;

public class UI {
	
	public static void printBoard(PecaXadres[][] pecaXadres) {
		for (int i=0; i<pecaXadres.length; i++) {
			System.out.print((8-i) + " ");
			for (int j=0; j<pecaXadres.length; j++) {
			printPiece(pecaXadres[i][j]);	
			}
			System.out.println();
		}
		
		System.out.println("  a b c d e f g h");
		
	}
	
	private static void printPiece(PecaXadres pecaXadres) {
		if (pecaXadres == null) {
			System.out.print("- ");
		}
		else {
			System.out.print(pecaXadres);
		}
		System.out.print("");
	}

}
