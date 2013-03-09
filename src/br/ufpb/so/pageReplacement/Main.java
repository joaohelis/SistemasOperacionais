package br.ufpb.so.pageReplacement;

import java.util.Scanner;


/**
 * @author Joao Helis Bernardo
 *
 */
public class Main {
		
		
	public static void main(String[] args){
		
		StorageDevice FIFO_HD,
					  LRU_HD;
		StorageDevice FIFO_RAM,
					  LRU_RAM;
		int[] inputSequence = Util.randomSequenceGenerate(15, 0, 9);		
//		int[] inputSequence = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
//		int[] inputSequence = {1, 0, 7, 9, 9, 3, 3, 7, 7, 9, 0, 3, 2, 6, 5, 9, 3, 2, 6, 3};
		System.out.println("Input Sequence: "+ Util.toStringSequence(inputSequence));
	
		FIFO fifoAlgorithm = new FIFO();
		LRU lruAlgorithm = new LRU();
		
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Operational System");
		System.out.println("-----------------------------------------------------------------------------------");		
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Page Replacement Algorithms");		
		int maxLengthRAM = 7;
		for(int lengthRAM = 3; lengthRAM <= maxLengthRAM; lengthRAM++){												
			FIFO_RAM = new StorageDevice(lengthRAM);
			LRU_RAM = new StorageDevice(lengthRAM);
			FIFO_HD = new StorageDevice(100);
			LRU_HD = new StorageDevice(100);
			Util.buildStorageDevice(inputSequence, FIFO_HD);
			Util.buildStorageDevice(inputSequence, LRU_HD);
			int fifoFaults = fifoAlgorithm.FIFO_WithReport(inputSequence, FIFO_HD, FIFO_RAM);
			int lruFaults = lruAlgorithm.LRU_QUEUE_WithReport(inputSequence, LRU_HD, LRU_RAM);
//			int lruFaults = lruAlgorithm.LRU_HEAP(inputSequence, LRU_HD, LRU_RAM);
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("-----------------------------------------------------------------------------------");		
			System.out.println("Input Sequence: "+ Util.toStringSequence(inputSequence));					
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("FIFO --> Length RAM: "+lengthRAM + " Faults: "+fifoFaults);			
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("LRU  --> Length RAM: "+lengthRAM + " Faults: "+lruFaults);
			String[] algorithms = {"FIFO", "LRU"};
			int option;			
			for(int i = 0; i < algorithms.length; ++i){
				System.out.println("-----------------------------------------------------------------------------------");
				while(true){					
					System.out.println("\n>>> Want to see report "+ algorithms[i] + " algorithm: ");
					System.out.println("1 - Yes");
					System.out.println("2 - No");				
					try{
						option = Integer.parseInt(new Scanner(System.in).nextLine());
						if(option >= 1 && option <= 2)
							break;
					}catch(NumberFormatException e){}
					System.out.println("-----------------------------------------------------------------------------------");
					System.out.println("> Invalid Option!");
					System.out.println("-----------------------------------------------------------------------------------");
				}
				if(algorithms[i].equalsIgnoreCase("FIFO") && option == 1)
					System.out.println(fifoAlgorithm.getReport());
				else if(algorithms[i].equalsIgnoreCase("LRU") && option == 1)
					System.out.println(lruAlgorithm.getReport());
			}
		}	
		System.out.println("-----------------------------------------------------------------------------------");
	}
	

}
