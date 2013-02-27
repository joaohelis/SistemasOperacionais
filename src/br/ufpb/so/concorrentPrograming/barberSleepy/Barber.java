package br.ufpb.so.concorrentPrograming.barberSleepy;


/**
 * @author Joao Helis Bernardo
 *
 */
public class Barber implements Runnable{
	
	private String nameBarber;	
	private BarberShop barberShop;	
	
	public Barber(String nameBarber, BarberShop barberShop) {		
		this.nameBarber = nameBarber;
		this.barberShop = barberShop;				
	}	
			
	public String getNameBarber() {
		return nameBarber;
	}

	public void setNameBarber(String nameBarber) {
		this.nameBarber = nameBarber;
	}
	
	public BarberShop getBarberShop() {
		return barberShop;
	}

	public void setBarberShop(BarberShop barberShop) {
		this.barberShop = barberShop;
	}
	
	public synchronized void working(){
		barberShop.working(this);		
		while(true)							
			barberShop.hairCut();
	}
	
	@Override
	public void run(){
		working();			
	}
	
}
