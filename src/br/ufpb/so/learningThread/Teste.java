package br.ufpb.so.learningThread;

/**
 * @author Joao Helis Bernardo
 *
 */
public class Teste {
	
	public static void main(String[] args) {
		
		Programa p1 = new Programa(1);
		Thread t1 = new Thread(p1);
		t1.start();
		
		Programa p2 = new Programa(2);
		Thread t2 = new Thread(p2);
		t2.start();
		
	}

}
