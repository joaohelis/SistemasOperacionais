package br.ufpb.so.concorrentPrograming.barberSleepy;


/**
 * @author Joao Helis Bernardo
 *
 */
public class Client implements Runnable{
	
	private String nameClient;	
	private BarberShop barberShop;
	
	public Client(String nameClient, BarberShop barberShop) {
		this.nameClient = nameClient;
		this.barberShop = barberShop;		
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public BarberShop getBarberShop() {
		return barberShop;
	}

	public void setBarberShop(BarberShop barberShop) {
		this.barberShop = barberShop;
	}
	
	@Override
	public void run(){				
		this.barberShop.enterBarberShop(this);					
	}
	
}
