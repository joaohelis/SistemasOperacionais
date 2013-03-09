package br.ufpb.so.pageReplacement;

import br.ufpb.ed.praticas.pratica06.heapBinarioMinimoGeneric.HeapElement;

/**
 * @author Joao Helis Bernardo
 *
 */
public class InfoPage implements HeapElement{
		
	private static int count;
	private int IDPage;
	private int priority;
	
	private InfoPage(int IDPage, int priority) {
		this.IDPage = IDPage;
		this.priority = priority;
	}

	public InfoPage(int IDPage) {		
		this(IDPage, 0);
	}

	public int getIDPage() {
		return IDPage;
	}

	public void setIDPage(int indexPage) {
		this.IDPage = indexPage;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int count) {
		this.priority = count;
	}

	@Override
	public int compareTo(HeapElement o) {
		if (getComparator() < o.getComparator())
			return -1;
		else if(getComparator() > o.getComparator())
			return 1;
		return 0;
	}

	@Override
	public Integer getComparator() {
		return priority;
	}

	@Override
	public void setComparator(Integer c) {
		this.priority = c;
	}
	
	public static int countIncrease(){
		return ++count;
	}

	@Override
	public String toString() {
		return "InfoPage [IDPage=" + IDPage + ", priority=" + priority + "]";
	}
}
