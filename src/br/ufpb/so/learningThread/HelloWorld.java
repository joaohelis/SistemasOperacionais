package br.ufpb.so.learningThread;

/**
 * @author Joao Helis Bernardo
 *
 */

public class HelloWorld implements Runnable {

	@Override
	public void run() {
		System.out.println("Hello World");

	}
	
	public static void main(String[] args) {
		HelloWorld hello = new HelloWorld();
		Thread t = new Thread(hello);
		t.start();
	}

}
