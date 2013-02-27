package br.ufpb.so.concorrentPrograming.barberSleepy;

/**
 * @author Joao Helis Bernardo
 *
 */

public class Factory {
	
	public static void main(String[] args) {
		
		int quantityBarbers = 1,
			quantityBarberChairs = quantityBarbers;
		
		Thread[] barbers = new Thread[quantityBarbers];
		String[] nameBarbers = {"Estraga"};
		
		BarberShop barberShop = new BarberShop(5, quantityBarbers, quantityBarberChairs);		
		
		for(int i = 0; i < barbers.length; ++i)
			barbers[i] = new Thread(new Barber(nameBarbers[i], barberShop));
		
		String[] nameClients = {"Joao", "Jose", "Pedro", "Mateus", "Lucas", "Luiz", "Juan", "Danilo", "Jefferson"};
		int quantityClients = nameClients.length;
		Thread[] clients = new Thread[quantityClients];		
		
		for(int i = 0; i < clients.length; ++i)			
			clients[i] = new Thread(new Client(nameClients[i], barberShop));
		
		/// BarberShop
		
		for(int i = 0; i < barbers.length; ++i)
			barbers[i].start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		
		for(int i = 0; i < clients.length; ++i)
			clients[i].start();		
	}
}
