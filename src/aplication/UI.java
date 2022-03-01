package aplication;


import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Color;
import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class UI {
	
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
		
	public static void limpaTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		}
		
	public static PosicaoXadrez lerPosicaoXadrez(Scanner sc) {
		try {
		String s = sc.nextLine();
		char coluna = s.charAt(0);
		int linha = Integer.parseInt(s.substring(1));
		return new PosicaoXadrez(coluna, linha);
		}
		catch (RuntimeException e){
			throw new InputMismatchException("Erro ao ler Posi��o de Partida. Valores v�o de a1 a h8");			
		}
	}
	
	public static void printMatch(PartidaDeXadrez partidadeXadrez, List<PecaXadrez> capturada) {
		 printBoard(partidadeXadrez.getPecas());
		 System.out.println();
		 printPecasCapturadas(capturada);
		 System.out.println();
		 System.out.println("Turno: "+ partidadeXadrez.getTurno());
		 if(!partidadeXadrez.getCheckMate()) {
			 System.out.println("Esperando jogador: " + partidadeXadrez.getJogadorAtual());
			 if(partidadeXadrez.getCheck()) {
				 System.out.println("CHECK");
			 }
		 }
		 else {
			 System.out.println("CHECKMATE");
			 System.out.println("VENCEDOR: " + partidadeXadrez.getJogadorAtual());
			 
		 }
	}
	
	public static void printBoard(PecaXadrez[][] pecaXadres) {
		for (int i=0; i<pecaXadres.length; i++) {
			System.out.print((8-i) + " ");
			for (int j=0; j<pecaXadres.length; j++) {
				printPiece(pecaXadres[i][j], false);	
			}
			System.out.println();
		}
		
		System.out.println("  a b c d e f g h");
		
	}
	
	public static void printBoard(PecaXadrez[][] pecaXadres, boolean[][] possiveisMovimentos) {
		for (int i=0; i<pecaXadres.length; i++) {
			System.out.print((8-i) + " ");
			for (int j=0; j<pecaXadres.length; j++) {
				printPiece(pecaXadres[i][j], possiveisMovimentos[i][j]);	
			}
			System.out.println();
		}
		
		System.out.println("  a b c d e f g h");
		
	}
	
	private static void printPiece(PecaXadrez pecaXadres, boolean background) {
		if (background == true) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (pecaXadres == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (pecaXadres.getCor() == Color.BRANCO) {
                System.out.print(ANSI_WHITE + pecaXadres + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + pecaXadres + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	private static void printPecasCapturadas(List<PecaXadrez> capturadas) {
		List<PecaXadrez> brancas = capturadas.stream().filter(x -> x.getCor() == Color.BRANCO).collect(Collectors.toList());
		List<PecaXadrez> pretas = capturadas.stream().filter(x -> x.getCor() == Color.PRETO).collect(Collectors.toList());
		System.out.println("Pe�as capturadas: ");
		System.out.print("Brancas: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(brancas.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print("Pretas: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(pretas.toArray()));
		System.out.print(ANSI_RESET);
	}
}
