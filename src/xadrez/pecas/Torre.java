package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString(){
		return "T";
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		//acima
		p.setPosicao(posicao.getLinha()-1, posicao.getColuna());
		
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda
		p.setPosicao(posicao.getLinha(), posicao.getColuna() - 1);
				
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//direita
		p.setPosicao(posicao.getLinha(), posicao.getColuna() + 1);
				
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
				
		//abaixo
		p.setPosicao(posicao.getLinha() + 1, posicao.getColuna());
				
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
				
		
		
		return mat;
	}
}
