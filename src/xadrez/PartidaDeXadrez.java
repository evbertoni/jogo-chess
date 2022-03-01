package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private int turno;
	private Color jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Color.BRANCO;
		check = false;
		setupInicial();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Color getJogadorAtual(){
		return jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
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
	
	public boolean[][] possiveisMovimentos(PosicaoXadrez posicaoInicial){
		Posicao posicao = posicaoInicial.toPosicao();
		validarPosicaoInicial(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentos();
	}
	
	public PecaXadrez performMovimentoXadrez(PosicaoXadrez posicaoInicial, PosicaoXadrez posicaoAlvo) {
		Posicao inicial = posicaoInicial.toPosicao();
		Posicao alvo = posicaoAlvo.toPosicao();
		validarPosicaoInicial(inicial);
		validarPosicaoAlvo(inicial, alvo);
		Peca pecaCapturada = fazerMov(inicial, alvo);
		
		if (testCheck(jogadorAtual)) {
			desfazerMov(inicial, alvo, pecaCapturada);
			throw new ExcessaoXadrez("Você não pode se colocar em cheque");
		}
		
		check = (testCheck(oponente(jogadorAtual))) ? true : false;
		
		proximoTurno();
		return (PecaXadrez)pecaCapturada;
		
	}
	
	private Peca fazerMov(Posicao inicial, Posicao alvo) {
		Peca p = tabuleiro.removerPeca(inicial);
		Peca pecaCapturada = tabuleiro.removerPeca(alvo);
		tabuleiro.lugarPeca(p, alvo);
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
	}
	
	private void desfazerMov(Posicao inicial, Posicao alvo, Peca pecaCapturada) {
		Peca p = tabuleiro.removerPeca(alvo);
		tabuleiro.lugarPeca(p, inicial);
		
		if (pecaCapturada != null) {
			tabuleiro.lugarPeca(pecaCapturada, alvo);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	
	private void validarPosicaoInicial(Posicao posicao) {
		if(!tabuleiro.existePeca(posicao)) {
			throw new ExcessaoXadrez("Não existe peça na posição de origem");
		}
		if(jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new ExcessaoXadrez("A peça escolhida não é sua");
		}
		if (!tabuleiro.peca(posicao).existeMovimentoPossivel()) {
			throw new ExcessaoXadrez("Não existe movimentos possiveis para a peça escolhida");
		}
	}
	
	private void validarPosicaoAlvo(Posicao inicial, Posicao alvo) {
		if (!tabuleiro.peca(inicial).possivelMovimento(alvo)) {
			throw new ExcessaoXadrez("A peça escolhida não pode se mover para a posição de destino");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
		
	}
	
	private Color oponente(Color cor) {
		return (cor == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
	}
	
	private PecaXadrez Rei(Color cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor()==cor).collect(Collectors.toList());
		for(Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe rei na cor "+cor);
	}
	
	private boolean testCheck(Color cor) {
		Posicao posicaoRei = Rei(cor).getPosicaoXadrez().toPosicao();
		List<Peca> pecasdoOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor()==oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecasdoOponente) {
			boolean[][] mat = p.possiveisMovimentos();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private void posicaoNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	


	
	
	private void setupInicial() {
		posicaoNovaPeca('b', 6, new Torre(tabuleiro, Color.PRETO));
		posicaoNovaPeca('e', 8, new Rei(tabuleiro, Color.BRANCO));
		posicaoNovaPeca('f', 4, new Torre(tabuleiro, Color.BRANCO));
		posicaoNovaPeca('g', 3, new Rei(tabuleiro, Color.PRETO));
		posicaoNovaPeca('c', 6, new Rei(tabuleiro, Color.PRETO));
	}
	
}
