package aplication;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcessaoXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;


public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaXadrez = new PartidaDeXadrez();
		List<PecaXadrez> capturadas = new ArrayList();
		
		while(!partidaXadrez.getCheckMate()) {
			try {
			UI.limpaTela();
			UI.printMatch(partidaXadrez, capturadas);
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
			
			if (pecaCapturada != null) {
				capturadas.add(pecaCapturada);
			}
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
		
		UI.limpaTela();
		UI.printMatch(partidaXadrez, capturadas);
	}
}
