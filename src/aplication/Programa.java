package aplication;

import xadres.PartidaDeXadres;

public class Programa {

	public static void main(String[] args) {
		
		PartidaDeXadres partidaXadres = new PartidaDeXadres();
		UI.printBoard(partidaXadres.getPecas());
	}

}
