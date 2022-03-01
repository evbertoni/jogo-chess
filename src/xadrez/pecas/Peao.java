package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{

	public Peao(Tabuleiro tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString(){
		return "P";
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		if (getCor() == Color.BRANCO) {
			p.setPosicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
			
			p.setPosicao(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			
			
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().existePeca(p2) && getContadorMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
			
			p.setPosicao(posicao.getLinha() - 1, posicao.getColuna()-1);
			if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
			
			p.setPosicao(posicao.getLinha() - 1, posicao.getColuna()+1);
			if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
			
			
		}
		else {
			p.setPosicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
			
			p.setPosicao(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			
			
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().existePeca(p2) && getContadorMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
			
			p.setPosicao(posicao.getLinha() + 1, posicao.getColuna()-1);
			if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
			
			p.setPosicao(posicao.getLinha() + 1, posicao.getColuna()+1);
			if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
		}
		
		
		return mat;
	}

}
