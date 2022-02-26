package xadres;

import tabuleiro.Peca;
import tabuleiro.Tabuleiro;

public class PecaXadres extends Peca {

		private Color cor;

		public PecaXadres(Tabuleiro tabuleiro, Color cor) {
			super(tabuleiro);
			this.cor = cor;
		}

		public Color getCor() {
			return cor;
		}

		
}
