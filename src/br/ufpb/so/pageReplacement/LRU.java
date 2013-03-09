package br.ufpb.so.pageReplacement;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Joao Helis Bernardo
 *
 */
public class LRU {
	
	private static String report;
			
	public int LRU_QUEUE_WithReport(int[] inputSequence, StorageDevice HD, StorageDevice RAM){
		report = "-----------------------------------------------------------------------------------" + "\n" +
				   ">>> LRU"+ "\n" +
				   "-----------------------------------------------------------------------------------" + "\n" +
				   "HD:  "+ HD + "\n" +
				   "RAM: "+ RAM + "\n";		
		int faults = 0;						
		Queue<Integer> pagesQueueRAM = new LinkedBlockingQueue<Integer>(RAM.capacity());
		String auxUI = "^";
		int i = 0;
		for(int page :inputSequence){			
			report += "-----------------------------------------------------------------------------------" + "\n" +
					 "Iteration LRU: "+ (i+1) + "\n" +			
					 "-----------------------------------------------------------------------------------" + "\n" +
					 "\nHD:  "+ HD + "\n" +
					 "RAM: "+ RAM + "\n" + "\n" +					
					 ">>> INPUT SEQUENCE: \n\n"+ Util.toStringSequence(inputSequence) + "\n" +
					 auxUI + "\n";
					 auxUI = "-  " + auxUI;			
			if(!RAM.contains(page)){
				report += ">>> 1 Fault: The page is not in memory <<<" + "\n";
				try {															
					Util.swap_in(page, RAM, HD);						
					report += "\n>>> SWAP IN: "+inputSequence[i] + "\n";
				}catch (PageReplacementException e){					
					if(e instanceof UnavailableSpaceException){ // Memoria RAM nao tem espaco						
						Integer pageSelectLRU = pagesQueueRAM.remove();														
						Util.replacePage(pageSelectLRU, page, RAM, HD);
						report += "\n>>> Storage space in main memory unavailable <<<" + "\n" +												
								 "\n>>> REPLACE PAGE: " + "\n" +
								 ">>> >>> SWAP OUT: "+pageSelectLRU + "\n" +
								 ">>> >>> SWAP IN : "+inputSequence[i] + "\n";
					}else if(e instanceof PageNotFoundException)
						throw new PageReplacementException("invalidates input sequence");									
				}				
				report += "\nHD:  "+ HD + "\n" +
						  "RAM: "+ RAM + "\n";				
				faults++;
			}else{
				report += "\n>>> The page "+inputSequence[i]+" is already in the main memory <<<" + "\n";
				pagesQueueRAM.remove(page);
			}			
			pagesQueueRAM.add(page);
			report += "\nQUEUE: "+pagesQueueRAM.toString() +"\n";
			report += "\n>>> Total faults: "+faults + "\n" +
				  "-----------------------------------------------------------------------------------\n";					
			++i;
		}
		return faults;
	}
	
	
	public static int LRU_QUEUE(int[] inputSequence, StorageDevice HD, StorageDevice RAM){				
		int faults = 0;						
		Queue<Integer> pagesQueueRAM = new LinkedBlockingQueue<Integer>(RAM.capacity());
		for(int page :inputSequence){			
			if(!RAM.contains(page)){				
				try {															
					Util.swap_in(page, RAM, HD);														
				}catch (PageReplacementException e){					
					if(e instanceof UnavailableSpaceException){ // Memoria RAM nao tem espaco						
						Integer pageSelectLRU = pagesQueueRAM.remove();															
						Util.replacePage(pageSelectLRU, page, RAM, HD);
					}else if(e instanceof PageNotFoundException)
						throw new PageReplacementException("invalidates input sequence");									
				}				
				faults++;
			}else				
				pagesQueueRAM.remove(page);
			pagesQueueRAM.add(page);
		}
		return faults;
	}

	public String getReport(){
		return report;
	}
	
}

//public static  int LRU_HEAP(int[] inputSequence, StorageDevice HD, StorageDevice RAM){
//report = "-----------------------------------------------------------------------------------" + "\n" +
//		   ">>> LRU"+ "\n" +
//		   "-----------------------------------------------------------------------------------" + "\n" +
//		   "HD:  "+ HD + "\n" +
//		   "RAM: "+ RAM + "\n";		
//int faults = 0;				
//MinBinaryHeap<InfoPage> minPriorityHeap = new MinBinaryHeap<InfoPage>();
//InfoPage[] sequenceInfoPage = buildInfoPageSequence(inputSequence);
//Map<Integer, InfoPage> controlPageOnHeapMAP = new HashMap<Integer, InfoPage>(); // Controla quem esta ou nao na heap		
//String auxUI = "^";
//for(int i = 0; i < sequenceInfoPage.length; ++i){			
//	minPriorityHeap.print();
//	report += "-----------------------------------------------------------------------------------" + "\n" +
//			 "Iteration LRU: "+ (i+1) + "\n" +			
//			 "-----------------------------------------------------------------------------------" + "\n" +
//			 "\nHD:  "+ HD + "\n" +
//			 "RAM: "+ RAM + "\n" + "\n" +					
//			 ">>> INPUT SEQUENCE: \n\n"+ Util.toStringSequence(inputSequence) + "\n" +
//			 auxUI + "\n";
//			 auxUI = "-  " + auxUI;
//	if(!RAM.contains(sequenceInfoPage[i].getIDPage())){
//		report += ">>> 1 Fault: The page is not in memory <<<" + "\n";
//		try {															
//			Util.swap_in(sequenceInfoPage[i].getIDPage(), RAM, HD);
//			report += "\n>>> SWAP IN: "+inputSequence[i] + "\n";
//		}catch (PageReplacementException e){					
//			if(e instanceof UnavailableSpaceException){ // Memoria RAM nao tem espaco						
//				InfoPage infoPageLRU = (InfoPage)minPriorityHeap.heapExtractMin();			
//				controlPageOnHeapMAP.remove(infoPageLRU.getIDPage());
//				report += "\n>>> Storage space in main memory unavailable <<<" + "\n" +												
//						 "\n>>> REPLACE PAGE: " + "\n" +
//						 ">>> >>> SWAP OUT: "+infoPageLRU.getIDPage() + "\n" +
//						 ">>> >>> SWAP IN : "+inputSequence[i] + "\n";							
//				Util.replacePage(infoPageLRU.getIDPage(), sequenceInfoPage[i].getIDPage(), RAM, HD);
//			}else if(e instanceof PageNotFoundException)
//				throw new PageReplacementException("invalidates input sequence");									
//		}							
//		faults++;
//		report += "\nHD:  "+ HD + "\n" +
//				  "RAM: "+ RAM + "\n";				
//	}else
//		report += "\n>>> The page '"+inputSequence[i]+"' is already in the main memory <<<" + "\n";						
//	if(!controlPageOnHeapMAP.containsKey(sequenceInfoPage[i].getIDPage())){								
//		minPriorityHeap.minHeapInsert(sequenceInfoPage[i]);													
//		controlPageOnHeapMAP.put(sequenceInfoPage[i].getIDPage(), sequenceInfoPage[i]);	
//		report += "\nIsering page: '" + sequenceInfoPage[i].getIDPage()+"' on Heap";				
//	}						
//	minPriorityHeap.updatePriority(minPriorityHeap.find(sequenceInfoPage[i]), InfoPage.countIncrease());				
//	report += "\nIncrease Priority of Page '"+ sequenceInfoPage[i].getIDPage() + "' for "+ (sequenceInfoPage[i].getComparator()) + "\n";
//	
//	report += "\n>>> Total faults: "+faults + "\n" +
//			  "-----------------------------------------------------------------------------------\n";
//}	
//return faults;
//}


//private InfoPage[] buildInfoPageSequence(int[] inputSequence){				
//InfoPage[] infoPageSequence = new InfoPage[inputSequence.length];
//Map<Integer, InfoPage> control = new HashMap<Integer, InfoPage>();
//for(int i = 0; i < inputSequence.length; ++i){
//	if (control.containsKey(inputSequence[i]))
//		infoPageSequence[i] = control.get(inputSequence[i]);
//	else{
//		InfoPage infoPage = new InfoPage(inputSequence[i]);
//		infoPageSequence[i] = infoPage;
//		control.put(infoPage.getIDPage(), infoPage);
//	}
//}
//return infoPageSequence;
//}