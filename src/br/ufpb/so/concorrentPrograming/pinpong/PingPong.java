

package br.ufpb.so.concorrentPrograming.pinpong;

import java.util.concurrent.Semaphore;

/**
 * @author Joao Helis Bernardo
 *
 */

public class PingPong {
	
	private Semaphore semaphore = new Semaphore(1,true);
	
	private String[] words = {"Ping", "Pong"};
	
	private int indexRandomWord = (int)(Math.random() * words.length),
			    indexRandomWord2 = (indexRandomWord + 1) % words.length;			  
	
	private MessageThread m1 = new MessageThread(semaphore, words[indexRandomWord]),
						  m2 = new MessageThread(semaphore, words[indexRandomWord2]);						  
			
	public void imprimir(){
		if (m1.getMessage().equalsIgnoreCase("Ping")){
			m1.start();
			m2.start();
		}else{
			m2.start();
			m1.start();
		}
	}
}
