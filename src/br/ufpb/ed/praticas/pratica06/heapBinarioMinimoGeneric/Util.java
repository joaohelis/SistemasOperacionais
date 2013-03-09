package br.ufpb.ed.praticas.pratica06.heapBinarioMinimoGeneric;


public class Util {
	
	public static String toStringArray(int[] array){
		return toStringArray(array, 0, array.length-1, "");
	}
	
	private static String toStringArray(int[] array, int posInicial, int posFinal, String aux){
		if (posInicial == posFinal){
			aux += Integer.toString(array[posInicial]); 
			return aux;							
		}else{
			String temp = Integer.toString(array[posInicial]);
			return toStringArray(array, ++posInicial, posFinal, aux + temp + ", ");
		}
	}
		
	public static int[] increaseDecreaseVector(int[] vector, int qtde, actionVector action){
		int length = vector.length;
		if(action == actionVector.decrease){
			length -= qtde;
			qtde = -qtde;
		}	
		int[] aux = new int[vector.length + qtde];
		for(int i = 0; i < length; ++i)
			aux[i] = vector[i];
		return aux;
	}
	
	public static int randint(int min, int max){ // Gera um n�mero aleat�riamente no itervalo [min, max]		
		return (int)(Math.random() * (max + 1 - min)) + min;  
	}
	
	public static enum actionVector{
		increase, decrease;
	}

	public static void swap(Object[] array, int i, int j) {		
		Object aux = array[i];
		array[i] = array[j];
		array[j] = aux;
	}

}
