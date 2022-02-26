package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString(){
		return "R";
	}

}
