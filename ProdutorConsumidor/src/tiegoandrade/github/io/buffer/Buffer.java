package tiegoandrade.github.io.buffer;

/**
 * Esta classe representa o Buffer, 
 * que é compartilhado entre o produtor e o consumidor.
 * @author Tiego
 */
public class Buffer {
	
	/**
	 * Tamanho do Buffer
	 */
	private static final int TAMANHO = 5;
	
	/**
	 * Array de inteiros que representa o buffer
	 */
	private int[] b = new int [TAMANHO];
	
	/**
	 * Controla a posição onde o próximo elemento deve ser inserido do buffer
	 */
	private int posInsere;
	
	/**
	 * Controla a posição onde o próximo elemento deve ser removido do buffer
	 */
	private int posRemove;
	
	/**
	 * Controla a quantidade de itens no buffer não consumidos.
	 */
	private int qtdeItens;
	
	/**
	 * Insere um item no buffer.
	 * Somente uma thread por vez pode chamar esse método.
	 * @param item: Item a ser inserido.
	 */
	public synchronized void inserir(int item) {
		
		/*
		 * Se o buffer estiver cheio, 
		 * o produtor deve dormir,
		 * aguardando que existam espaços no buffer.
		 */
		while (qtdeItens == TAMANHO) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		
		/*
		 * Se o buffer estiver vazio, o produtor é acordado para inserir mais itens.
		 */
		if (qtdeItens == 0) {
			notifyAll();
		}
		
		/*
		 * Insere um item no buffer.
		 * O buffer é uma  fila circular.
		 */
		b[posInsere] = item;
		posInsere = (posInsere + 1) % TAMANHO;
		qtdeItens++;
	
		// Imprime o conteúdo do buffer no console.
		imprimir();
		}
	
	/**
	 * Remove um item do buffer.
	 * Apenas uma Thread por vez pode utilizar esse método.
	 * @return: Item removido.
	 */
	public synchronized int remover() {
		
		/*
		 * Se o buffer estiver vazio, o consumidor deve dormir,
		 * aguardando até que algum item seja produzido.
		 */
		while (qtdeItens == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		
		/*
		 * Se o buffer estiver cheio, o consumidor é acordado para consumir os itens.
		 */
		if (qtdeItens == TAMANHO) {
			notifyAll();
		}	
		
		/*
		 * Remove um item no buffer.
		 * O buffer é uma fila circular.
		 */
		int item = b[posRemove];
		posRemove = (posRemove +1 ) % TAMANHO;
		qtdeItens--;
		
		// Imprime o conteúdo do buffer no console.
		imprimir();
		
		// Retorna o item que foi removido.
		return item;
		}
	
	/**
	 * Imprime o conteúdo do buffer.
	 */
	private synchronized void imprimir() {
		
		// Representa o próximo item que será removido.
		int i = posRemove;
		
		// Representa o itens que se encontram no buffer.
		int qtdeImpressos = 0;
		
		// Informa se o buffer está cheio ou vazio.
		boolean vazio = true;
		
		// Loop que imprime todos os itens do buffer
		while (qtdeImpressos < qtdeItens) {
			vazio = false;
			System.out.println(b[i] + " ");
			i = (i + 1) % TAMANHO;
			qtdeImpressos++;
		}
		
		// Imprime um "-" se o buffer estiver vazio.
		if (vazio) {
			System.out.println("-");
		}
		
		System.out.println();
	}
}
	
