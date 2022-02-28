package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		setupInicial();
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	Torre torre = new Torre(tabuleiro, Color.PRETO);
	Posicao posicaotorre = new Posicao(2,1);
	
	private void setupInicial() {
		tabuleiro.lugarPeca(torre, posicaotorre);
		tabuleiro.lugarPeca(new Rei(tabuleiro, Color.BRANCO), new Posicao(3,1));
	}
	
}
