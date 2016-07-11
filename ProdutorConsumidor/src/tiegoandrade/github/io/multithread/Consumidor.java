package tiegoandrade.github.io.multithread;

import tiegoandrade.github.io.buffer.Buffer;

public class Consumidor extends Thread {
	
	/**
	 *  Quantidade de itens que devem ser consumidos.
	 */
	private int qtdeItens;
	
	/**
	 * Referência para o Buffer.
	 */
	private Buffer buffer;
	
	/**
	 * Construtor
	 * @param qtdeItens: Quantidade de itens a serem consumidos.
	 * @param buffer: Buffer com os itens.
	 */
	public Consumidor(int qtdeItens, Buffer buffer) {
		this.qtdeItens = qtdeItens;
		this.buffer = buffer;
	}
	
	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run () {
		
		
		// Consumidor fica em loop consumindo os itens até não ter mais itens.
		 for (int i = 1; i <= qtdeItens; i++) {
			
			 // Remove o item.
			 buffer.remover();
			
			 /**
			  * Depois de consumir um item, o consumidor dorme durante um tempo
			  * randômico. (entre 0 a 500ms). 
			  */
			 int tempo = (int) (Math.random() * 500);
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
