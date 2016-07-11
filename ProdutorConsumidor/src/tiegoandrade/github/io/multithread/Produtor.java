package tiegoandrade.github.io.multithread;

import tiegoandrade.github.io.buffer.Buffer;

/**
 * Thread que preresenta o produtor
 * Essa classe insere itens no buffer
 * @author Tiego
 */
public class Produtor extends Thread {
	
	/**
	 * Quantidade de itens que devem ser consumidos.
	 */
	private int qtdItens;
	
	/**
	 * Referência para o buffer
	 */
	private Buffer buffer;
	
	/**
	 * Construtor.
	 * @param qtdItens: Quantidade de itens a serem produzidos
	 * @param buffer: Buffer para colocar os itens
	 */
	public Produtor(int qtdItens, Buffer buffer) {
		this.qtdItens = qtdItens;
		this.buffer = buffer;
	}
	/**
	 * @see java.lang.Thread#run()
	 */
	@Override 
	public void run() {
		
		/*
		 * Produtor ficará em loop até produzir o máximo de itens permitido dentro do buffer.
		 */
		for (int i = 1; i <= qtdItens; i++) {
			
			int item = (int) (Math.random() * qtdItens);
			buffer.inserir(item);
			
			/*
			 * Depois de produzir um item,
			 * Produto dorme um tempo randômico (entre 0 a 500ms).
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
