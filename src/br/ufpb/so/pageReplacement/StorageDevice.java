package br.ufpb.so.pageReplacement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * @author Joao Helis Bernardo
 *
 */
public class StorageDevice{
		
	private Vector<Page<?>> storageSpaceList;
	private Map<Integer, Page<?>> storageSpaceMAP;
	
	public StorageDevice(int espacoMaximoArmazenamento){
		this(new Vector<Page<?>>(espacoMaximoArmazenamento), new HashMap<Integer, Page<?>>());
	}
	
	private StorageDevice(Vector<Page<?>> storageSpaceList,
			Map<Integer, Page<?>> storageSpaceMAP) {		
		this.storageSpaceList = storageSpaceList;
		this.storageSpaceMAP = storageSpaceMAP;
	}



	public Page<?> getPage(Integer pageID) throws PageReplacementException{
		if (!storageSpaceMAP.containsKey(pageID))
			throw new PageNotFoundException("Page Not Found");
		return this.storageSpaceMAP.get(pageID);
	}
	
	public Page<?> getPageByIndex(Integer index){
		return storageSpaceList.get(index);
	}
	
	public Page<?> removePage(Integer pageID){		
		Page<?> page = getPage(pageID);
		removePage(page);
		return page;
	}
	
	public boolean removePage(Page<?> page){
		storageSpaceMAP.remove(page.getId());
		return storageSpaceList.remove(page);
	}
	
	public void insertPage(Page<?> page) throws PageReplacementException{
		insertPage(storageSpaceList.size(), page);		
	}
	
	public void insertPage(int index, Page<?> page){
		if(storageSpaceList.capacity() < storageSpaceList.size() + 1)
			throw new UnavailableSpaceException("No Space in Memory");
		storageSpaceList.add(index, page);
		storageSpaceMAP.put(page.getId(), page);
	}
	
	public int indexOf(Page<?> page){
		return storageSpaceList.indexOf(page);
	}
	
	public int indexOf(Integer pageID){
		return indexOf(storageSpaceMAP.get(pageID));
	}
	
	public boolean isEmpty(){		
		return storageSpaceList.isEmpty();
	}
	
	public int capacity(){		
		return storageSpaceList.capacity();
	}	
	
	public boolean contains(Page<?> page){
		return contains(page.getId());
	}
	
	public boolean contains(Integer pageID){
		return storageSpaceMAP.containsKey(pageID);
	}
	
	public Iterator<Page<?>> iterator(){
		return storageSpaceList.iterator();
	}
	
	public String toString(){
		String aux = "Storage Device [";
		if(!storageSpaceList.isEmpty()){
			for(int i = 0; i < storageSpaceList.size()-1; i++)
				aux += storageSpaceList.get(i).toString() + ", ";
			aux += storageSpaceList.get(storageSpaceList.size()-1) +", (...) ";
		}
		aux += "'Empty Spaces' = "+ 
		  (storageSpaceList.capacity() - storageSpaceList.size() )+"] length = " +storageSpaceList.capacity();
		return aux;
	}
}
