package xadrez;

import tabuleiro.Peca;
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
	
	public PecaXadrez performMovimentoXadrez(PosicaoXadrez posicaoInicial, PosicaoXadrez posicaoAlvo) {
		Posicao inicial = posicaoInicial.toPosicao();
		Posicao alvo = posicaoAlvo.toPosicao();
		validarPosicaoInicial(inicial);
		Peca pecaCapturada = fazerMov(inicial, alvo);
		return (PecaXadrez)pecaCapturada;
		
	}
	
	private Peca fazerMov(Posicao inicial, Posicao alvo) {
		Peca p = tabuleiro.removerPeca(inicial);
		Peca pecaCapturada = tabuleiro.removerPeca(alvo);
		tabuleiro.lugarPeca(p, alvo);
		return pecaCapturada;
	}
	
	private void validarPosicaoInicial(Posicao posicao) {
		if(!tabuleiro.existePeca(posicao)) {
			throw new ExcessaoXadrez("Não existe peça na posição de origem");
		}
	}
	
	private void posicaoNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}
	
	Torre torre = new Torre(tabuleiro, Color.PRETO);

	
	
	private void setupInicial() {
		posicaoNovaPeca('b', 6, torre);
		posicaoNovaPeca('e', 8, new Rei(tabuleiro, Color.BRANCO));
		posicaoNovaPeca('f', 4, new Rei(tabuleiro, Color.BRANCO));
		posicaoNovaPeca('g', 3, new Rei(tabuleiro, Color.PRETO));
		posicaoNovaPeca('c', 6, new Rei(tabuleiro, Color.PRETO));
	}
	
}
