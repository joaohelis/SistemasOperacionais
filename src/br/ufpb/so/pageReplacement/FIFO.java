package br.ufpb.so.pageReplacement;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Joao Helis Bernardo
 *
 */
public class FIFO {
	
	private String report;
		
	public static int FIFO(int[] inputSequence, StorageDevice HD, StorageDevice RAM){				
		int faults = 0;
		Queue<Integer> pagesQueueRAM = new LinkedBlockingQueue<Integer>();		
		for(int i = 0; i < inputSequence.length; ++i)								
			if (!RAM.contains(inputSequence[i])){						
				try {															
					Util.swap_in(inputSequence[i], RAM, HD);														
				}catch (PageReplacementException e){					
					if(e instanceof UnavailableSpaceException){ // Memoria RAM nao tem espaco
						Integer pageID = pagesQueueRAM.remove();											
						Util.replacePage(pageID, inputSequence[i], RAM, HD);
					}else if(e instanceof PageNotFoundException)
						throw new PageReplacementException("invalidates input sequence");									
				}
				pagesQueueRAM.add(inputSequence[i]);
				faults++;
			}		
		return faults;
	}	
		
	public int FIFO_WithReport(int[] inputSequence, StorageDevice HD, StorageDevice RAM){
		report = "-----------------------------------------------------------------------------------" + "\n" +
						   ">>> FIFO"+ "\n" +
						   "-----------------------------------------------------------------------------------" + "\n" +
						   "HD:  "+ HD + "\n" +
						   "RAM: "+ RAM + "\n";				
		int faults = 0;
		Queue<Integer> pagesQueueRAM = new LinkedBlockingQueue<Integer>();
		String auxUI = "^";		
		for(int i = 0; i < inputSequence.length; ++i){			
			report += "-----------------------------------------------------------------------------------" + "\n" +
						 "Iteration FIFO: "+ (i+1) + "\n" +			
						 "-----------------------------------------------------------------------------------" + "\n" +
						 "\nHD:  "+ HD + "\n" +
						 "RAM: "+ RAM + "\n" + "\n" +					
						 ">>> INPUT SEQUENCE: \n\n"+ Util.toStringSequence(inputSequence) + "\n" +
						 auxUI + "\n";
						 auxUI = "-  " + auxUI;
			if (!RAM.contains(inputSequence[i])){		
				report += ">>> 1 Fault: The page is not in memory <<<" + "\n";
				try {															
					Util.swap_in(inputSequence[i], RAM, HD);					
					report += "\n>>> SWAP IN: "+inputSequence[i] + "\n";					
				}catch (PageReplacementException e){					
					if(e instanceof UnavailableSpaceException){ // Memoria RAM nao tem espaco
						Integer pageID = pagesQueueRAM.remove();
						report += "\n>>> Storage space in main memory unavailable <<<" + "\n" +												
									 "\n>>> REPLACE PAGE: " + "\n" +
									 ">>> >>> SWAP OUT: "+pageID + "\n" +
									 ">>> >>> SWAP IN : "+inputSequence[i] + "\n";						
						Util.replacePage(pageID, inputSequence[i], RAM, HD);
					}else if(e instanceof PageNotFoundException)
						throw new PageReplacementException("invalidates input sequence");									
				}
				pagesQueueRAM.add(inputSequence[i]);
				faults++;
				report += "\nHD:  "+ HD + "\n" +
							 "RAM: "+ RAM + "\n";			
			}else
				report += "\n>>> The page "+inputSequence[i]+" is already in the main memory <<<";			
			report += "\n>>> Total faults: "+faults + "\n" +
						 "-----------------------------------------------------------------------------------\n";
		}
		return faults;
	}

	public String getReport() {
		return report;
	}
}
