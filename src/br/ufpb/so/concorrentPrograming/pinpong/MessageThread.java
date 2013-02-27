package br.ufpb.so.concorrentPrograming.pinpong;

import java.util.concurrent.Semaphore;

/**
 * @author Joao Helis Bernardo
 *
 */

public class MessageThread extends Thread {
	
	private Semaphore semaphore;
	private String message;
	
	public MessageThread(Semaphore semaphore, String message){
		super();
		this.semaphore = semaphore;
		this.message = message;
	}
	
	@Override
	public void run(){
		while(true){
			try {
				semaphore.acquire();
				System.out.println(message);
				Thread.sleep(1000);
				semaphore.release();
			} catch (InterruptedException e){}
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
