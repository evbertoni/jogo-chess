package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private int turno;
	private Color jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Color.BRANCO;
		check = false;
		checkMate = false;
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
	
	public boolean getCheckMate() {
		return checkMate;
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
		
		if(testCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}
		else {
			proximoTurno();	
		}
				
		return (PecaXadrez)pecaCapturada;
		
	}
	
	private Peca fazerMov(Posicao inicial, Posicao alvo) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(inicial);
		p.incrementarContadorMov();
		Peca pecaCapturada = tabuleiro.removerPeca(alvo);
		tabuleiro.lugarPeca(p, alvo);
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
	}
	
	private void desfazerMov(Posicao inicial, Posicao alvo, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(alvo);
		p.decrementarContadorMov();
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
	
	private boolean testCheckMate(Color cor) {
		if (!testCheck(cor)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor()==cor).collect(Collectors.toList());
		
		for (Peca p : list) {
			boolean[][] mat = p.possiveisMovimentos();
			for (int i=0; i< tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao inicial = ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
						Posicao alvo = new Posicao(i,j);
						Peca pecaCapturada = fazerMov(inicial, alvo);
						boolean testCheck = testCheck(cor);
						desfazerMov(inicial, alvo, pecaCapturada);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void posicaoNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	


	
	
	private void setupInicial() {
		posicaoNovaPeca('a', 1, new Torre(tabuleiro, Color.BRANCO));
		posicaoNovaPeca('b', 1, new Cavalo(tabuleiro, Color.BRANCO));
		posicaoNovaPeca('c', 1, new Bispo(tabuleiro, Color.BRANCO));
		posicaoNovaPeca('d', 1, new Rainha(tabuleiro, Color.BRANCO));
        posicaoNovaPeca('e', 1, new Rei(tabuleiro, Color.BRANCO));
        posicaoNovaPeca('f', 1, new Bispo(tabuleiro, Color.BRANCO));
        posicaoNovaPeca('g', 1, new Cavalo(tabuleiro, Color.BRANCO));
        posicaoNovaPeca('h', 1, new Torre(tabuleiro, Color.BRANCO));
        posicaoNovaPeca('a', 2, new Peao(tabuleiro, Color.BRANCO));
        posicaoNovaPeca('b', 2, new Peao(tabuleiro, Color.BRANCO));
        posicaoNovaPeca('c', 2, new Peao(tabuleiro, Color.BRANCO));
        posicaoNovaPeca('d', 2, new Peao(tabuleiro, Color.BRANCO));
        posicaoNovaPeca('e', 2, new Peao(tabuleiro, Color.BRANCO));
        posicaoNovaPeca('f', 2, new Peao(tabuleiro, Color.BRANCO));
        posicaoNovaPeca('g', 2, new Peao(tabuleiro, Color.BRANCO));
        posicaoNovaPeca('h', 2, new Peao(tabuleiro, Color.BRANCO));

       
        posicaoNovaPeca('a', 8, new Torre(tabuleiro, Color.PRETO));
        posicaoNovaPeca('b', 8, new Cavalo(tabuleiro, Color.PRETO));
        posicaoNovaPeca('c', 8, new Bispo(tabuleiro, Color.PRETO));
        posicaoNovaPeca('d', 8, new Rainha(tabuleiro, Color.PRETO));
        posicaoNovaPeca('e', 8, new Rei(tabuleiro, Color.PRETO));
        posicaoNovaPeca('f', 8, new Bispo(tabuleiro, Color.PRETO));
        posicaoNovaPeca('g', 8, new Cavalo(tabuleiro, Color.PRETO));
        posicaoNovaPeca('h', 8, new Torre(tabuleiro, Color.PRETO));
        posicaoNovaPeca('a', 7, new Peao(tabuleiro, Color.PRETO));
        posicaoNovaPeca('b', 7, new Peao(tabuleiro, Color.PRETO));
        posicaoNovaPeca('c', 7, new Peao(tabuleiro, Color.PRETO));
        posicaoNovaPeca('d', 7, new Peao(tabuleiro, Color.PRETO));
        posicaoNovaPeca('e', 7, new Peao(tabuleiro, Color.PRETO));
        posicaoNovaPeca('f', 7, new Peao(tabuleiro, Color.PRETO));
        posicaoNovaPeca('g', 7, new Peao(tabuleiro, Color.PRETO));
        posicaoNovaPeca('h', 7, new Peao(tabuleiro, Color.PRETO));
	}
	
}
