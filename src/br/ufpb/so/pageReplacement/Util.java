package br.ufpb.so.pageReplacement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Joao Helis Bernardo
 *
 */
public class Util {
	
	public static String toStringSequence(int[] sequence){
		String toString = "";
		for(int i = 0; i < sequence.length; ++i)
			toString += (i == sequence.length -1)? Integer.toString(sequence[i]): (sequence[i] + ", ");		
		return toString;
	}	
	
	public static void buildStorageDevice(int[] sequence, StorageDevice storageDevice){
		Iterator<Page<?>> itPage = generatePage(sequence);
		while(itPage.hasNext())			
			storageDevice.insertPage(itPage.next());
	}
	
	public static Iterator<Page<?>> generatePage(int[] sequence){
		Map<Integer, Page<?>> pagesMAP = new HashMap<Integer, Page<?>>();
		List<Page<?>> pages = new LinkedList<Page<?>>();
		for(int i = 0; i < sequence.length; ++i){			
			if(!pagesMAP.containsKey(sequence[i])){
				Page<?> page = new Page(sequence[i]);
				pagesMAP.put(page.getId(), page);
				pages.add(page);
			}		
		}
		return pages.iterator();
	}
	
	public static void replacePage(Page<?> pageRAM, Page<?> pageHD, StorageDevice RAM, StorageDevice HD){
		replacePage(pageRAM.getId(), pageHD.getId(), RAM, HD);
	}
	
	public static void replacePage(Integer pageRAM_ID, Integer pageHD_ID, StorageDevice RAM, StorageDevice HD){
		int indexPageRAM = RAM.indexOf(pageRAM_ID);		
		int indexPageHD = HD.indexOf(pageHD_ID);				
		swap_out(pageRAM_ID, indexPageHD, RAM, HD);						
		swap_in(pageHD_ID, indexPageRAM, RAM, HD);
	}
	
	public static void swap_out(Page<?> page, StorageDevice RAM, StorageDevice HD) throws PageReplacementException{
		swap_out(page.getId(), RAM, HD);
	}
	
	public static void swap_out(Integer pageID, StorageDevice RAM, StorageDevice HD){
		HD.insertPage(RAM.getPage(pageID));
		RAM.removePage(pageID);
	}
	
	public static void swap_out(Integer pageID, int indexHD, StorageDevice RAM, StorageDevice HD){		
		HD.insertPage(indexHD, RAM.getPage(pageID));
		RAM.removePage(pageID);
	}
	
	public static void swap_out(Page<?> page, int indexHD, StorageDevice RAM, StorageDevice HD){
		swap_in(page.getId(), indexHD, RAM, HD);
	}
	
	public static void swap_in(Page<?> page, StorageDevice RAM, StorageDevice HD){
		swap_in(page.getId(), RAM, HD);
	}
	
	public static void swap_in(Integer pageID, StorageDevice RAM, StorageDevice HD){
		RAM.insertPage(HD.getPage(pageID));
		HD.removePage(pageID);
	}
	
	public static void swap_in(Integer pageID, int indexRAM, StorageDevice RAM, StorageDevice HD){		
		RAM.insertPage(indexRAM, HD.getPage(pageID));
		HD.removePage(pageID);
	}
	
	public static void swap_in(Page<?> page, int indexRAM, StorageDevice RAM, StorageDevice HD){
		swap_in(page.getId(), indexRAM, RAM, HD);
	}
	
	public static int[] randomSequenceGenerate(int quantity, int init, int end){
		int[] sequence = new int[quantity];
		for(int i = 0; i < quantity; ++i)
			sequence[i] = randint(init, end);
		return sequence;
	}
	
	public static int randint(int min, int max){ // Gera um numero aleatoriamente no itervalo [min, max]		
		return (int)(Math.random() * (max + 1 - min)) + min;		
	}

}
