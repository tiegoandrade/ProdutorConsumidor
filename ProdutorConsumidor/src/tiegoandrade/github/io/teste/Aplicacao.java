package tiegoandrade.github.io.teste;

import tiegoandrade.github.io.buffer.Buffer;
import tiegoandrade.github.io.multithread.Consumidor;
import tiegoandrade.github.io.multithread.Produtor;

public class Aplicacao {

	public static void main(String[] args) {
		
		Buffer buffer = new Buffer();
		
		Produtor p = new Produtor(100, buffer);
		
		p.start();
		
		Consumidor c = new Consumidor(100, buffer);
		
		c.start();

	}

}
