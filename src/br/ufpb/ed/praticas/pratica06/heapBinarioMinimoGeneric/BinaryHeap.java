package br.ufpb.ed.praticas.pratica06.heapBinarioMinimoGeneric;

public class BinaryHeap<T extends HeapElement> {
	
	protected HeapElement[] heap;	
	protected int size,
				increaseOrDecrease; // Representa o valor do incremento ou decremento do heap, pois o heap eh dinamico
	
	
	public BinaryHeap(HeapElement[] v){
		heap = v;
		this.size = v.length;
	}
	
	public BinaryHeap(int length){
		this(new HeapElement[length]);		
	}
	
	public BinaryHeap(){		
		this.increaseOrDecrease = 100;
		this.heap = new HeapElement[increaseOrDecrease];
	}
	
	protected static int parent(int i){
		return (int)((i-1)/2);		
	}
	
	protected static int childrenLeft(int i){
		return 2 * i + 1;
	}
	
	protected static int childrenRigth(int i){
		return 2 * i + 2;
	}
	
	public Iterator<HeapElement> iterator(){
		return new HeapIterator<HeapElement>(heap, size);
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	public boolean conteins(T v){
		Iterator<HeapElement> it = iterator();
		while(it.hasNext())
			if (it.next().equals(v))
				return true;
		return false;
	}
	
	public String toString(){
		String aux = "";
		Iterator<HeapElement> it = iterator();
		if(it.hasNext()){
			while(it.hasNext())
				aux += it.next()+", ";
			aux = aux.substring(0, aux.length()-2);
		}
		return aux;
	}

}
