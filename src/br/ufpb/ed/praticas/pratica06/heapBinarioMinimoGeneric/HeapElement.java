package br.ufpb.ed.praticas.pratica06.heapBinarioMinimoGeneric;

public interface HeapElement extends Comparable<HeapElement>{
	
	Integer getComparator();
	void setComparator(Integer c);

}
