package br.ufpb.so.concorrentPrograming.barberSleepy;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * @author Joao Helis Bernardo
 *
 */
public class BarberShop{
	
	private Semaphore roomChairsEmpty,					  
					  barberChairEmpty,
					  mutex;
	        		  	        
	private Queue<Client> clients;
	private Queue<Barber> barbers;		

	public BarberShop(int quantityRoomChairs, int maxQuantityBarbers, int quantityBarberChairs){						
		this.clients = new LinkedBlockingQueue<Client>();	
		this.barbers = new LinkedBlockingQueue<Barber>();
		// Semaphores
		this.roomChairsEmpty = new Semaphore(quantityRoomChairs);
		this.barberChairEmpty = new Semaphore(quantityBarberChairs);
		this.mutex = new Semaphore(1, true);
	}
	
	public void working(Barber barber){		
		synchronized (barber) {
			System.out.println("O barbeiro "+barber.getNameBarber().toUpperCase()+ " esta trabalhando na barbearia.");		
			this.barbers.add(barber);
			hairCut();
		}		
	}
	
	public void hairCut(){		
		try {							
			Barber barber = barbers.element();
			if (clients.size() >= 1){								
				synchronized (barber) {					
					if (barberChairEmpty.tryAcquire()){
						Client client = clients.element();
						System.out.println("O barbeiro "+barber.getNameBarber().toUpperCase()+" esta cortando cabelo do cliente "+
								client.getNameClient().toUpperCase());
						Thread.sleep((int)(Math.random() * 5000)); // Tempo para cortar o cabelo
						System.out.println(client.getNameClient().toUpperCase() + " teve o cabelo cortado :)");					
						exitBarberShop(client);
						barbers.add(barbers.remove()); 
						barberChairEmpty.release();
					}
				}				
			}else{
				System.out.println("Barbeiro "+barber.getNameBarber().toUpperCase()+" esta dormindo!");
				while(clients.isEmpty())					
					barber.wait();
			}																				
		} catch (InterruptedException e) {}			 		
	}

	public void enterBarberShop(Client client){
		synchronized (client) {
			try {
				mutex.acquire();
				if (roomChairsEmpty.tryAcquire()){									
					System.out.println(client.getNameClient().toUpperCase() + " esta entrando na barbearia!");
					waitHairCut(client);
				}else
					System.out.println("Barbearia Lotada! "+client.getNameClient().toUpperCase()+ " nao entrou :(");
			} catch (InterruptedException e) {}
			finally{
				mutex.release();
			}
		}
	}
	
	private void waitHairCut(Client client){
		this.clients.add(client);
		if (barberChairEmpty.availablePermits() >= barbers.size()){
			System.out.println(client.getNameClient().toUpperCase() + " acordou o barbeiro " + 
					   barbers.element().getNameBarber().toUpperCase());
			wakeBarber();										
		}		
	}
	
	private Barber wakeBarber(){		
		Barber barber = this.barbers.element(); 		
		synchronized (barber) {
			barber.notify();			
		}		
		return barber;				
	}
		
	private boolean exitBarberShop(Client client){
		System.out.println(client.getNameClient().toUpperCase() + " esta saindo da barbearia!");
		return clients.remove(client);
	}
	
	public int getQuantityBarbers(){
		return barbers.size();
	}
}
