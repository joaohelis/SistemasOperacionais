package br.ufpb.ed.praticas.pratica06.heapBinarioMinimoGeneric;


public class HeapIterator<T extends HeapElement> implements Iterator<T> {
	
	private T[] heap;
	
	private int size,
				ponteiro = -1;
	
	public HeapIterator(T[] heap, int size){
		this.heap = heap;
		this.size = size;
	}

	@Override
	public T next() {
		if(!hasNext())
			throw new IteratorException("No has Next");
		return heap[++ponteiro];
	}

	@Override
	public boolean hasNext() { 
		return (ponteiro < size-1 && size > 0);
	}

}
