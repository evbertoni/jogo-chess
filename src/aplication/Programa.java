package aplication;


import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.ExcessaoXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;


public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaXadrez = new PartidaDeXadrez();
		
		while(true) {
			try {
			UI.limpaTela();
			UI.printBoard(partidaXadrez.getPecas());
			System.out.println();
			System.out.print("Origem: ");
			PosicaoXadrez inicial = UI.lerPosicaoXadrez(sc);
			
			boolean[][] possiveisMovimentos = partidaXadrez.possiveisMovimentos(inicial);
			UI.limpaTela();
			UI.printBoard(partidaXadrez.getPecas(), possiveisMovimentos);
			
			System.out.println();
			System.out.print("Destino: ");
			PosicaoXadrez alvo = UI.lerPosicaoXadrez(sc);
			
			PecaXadrez pecaCapturada = partidaXadrez.performMovimentoXadrez(inicial, alvo);
		}
			catch (ExcessaoXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}				
	}
}
