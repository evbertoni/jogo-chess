package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez{

	public Cavalo(Tabuleiro tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString(){
		return "C";
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
		
		
		
		p.setPosicao(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			
		}
		
		
		
		p.setPosicao(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
				
		}
		
	
		
		p.setPosicao(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
				
		}
		
		
		
		p.setPosicao(posicao.getLinha() - 1, posicao.getColuna()+2);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
				
		}
		
		
		
		p.setPosicao(posicao.getLinha() + 1, posicao.getColuna() +2 );
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
				
		}
		
		//nordeste
		
		p.setPosicao(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
					
		}
		
		//sudoeste
		
		p.setPosicao(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
						
		}
		
		//sudeste
		
		p.setPosicao(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
						
		}
		
		
		
		return mat;
	}

}
