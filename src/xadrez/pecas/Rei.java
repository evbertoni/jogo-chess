package xadrez.pecas;

import tabuleiro.Posicao;
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

	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		if (p == null || p.getCor() != getCor()){
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		//acima
		
		p.setPosicao(posicao.getLinha() - 1, posicao.getColuna());
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			
		}
		
		//direita
		
		p.setPosicao(posicao.getLinha(), posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
				
		}
		
		//esquerda
		
		p.setPosicao(posicao.getLinha(), posicao.getColuna() - 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
				
		}
		
		//abaixo
		
		p.setPosicao(posicao.getLinha() + 1, posicao.getColuna());
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
				
		}
		
		//noroeste
		
		p.setPosicao(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
				
		}
		
		//nordeste
		
		p.setPosicao(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
					
		}
		
		//sudoeste
		
		p.setPosicao(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
						
		}
		
		//sudeste
		
		p.setPosicao(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
						
		}
		
		
		
		return mat;
	}

}
