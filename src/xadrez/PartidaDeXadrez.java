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
		validarPosicaoAlvo(inicial, alvo);
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
			throw new ExcessaoXadrez("N�o existe pe�a na posi��o de origem");
		}
		if (!tabuleiro.peca(posicao).existeMovimentoPossivel()) {
			throw new ExcessaoXadrez("N�o existe movimentos possiveis para a pe�a escolhida");
		}
	}
	
	private void validarPosicaoAlvo(Posicao inicial, Posicao alvo) {
		if (!tabuleiro.peca(inicial).possivelMovimento(alvo)) {
			throw new ExcessaoXadrez("A pe�a escolhida n�o pode se mover para a posi��o de destino");
		}
	}
	
	private void posicaoNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}
	


	
	
	private void setupInicial() {
		posicaoNovaPeca('b', 6, new Torre(tabuleiro, Color.PRETO));
		posicaoNovaPeca('e', 8, new Rei(tabuleiro, Color.BRANCO));
		posicaoNovaPeca('f', 4, new Torre(tabuleiro, Color.BRANCO));
		posicaoNovaPeca('g', 3, new Rei(tabuleiro, Color.PRETO));
		posicaoNovaPeca('c', 6, new Rei(tabuleiro, Color.PRETO));
	}
	
}
