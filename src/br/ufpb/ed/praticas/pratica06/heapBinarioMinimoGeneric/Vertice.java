package br.ufpb.ed.praticas.pratica06.heapBinarioMinimoGeneric;

public class Vertice implements Comparable<Vertice>{
	
	private int id,
				chave;
	private String lable;
	private Vertice pai;
	
	public Vertice(int id, String lable) {
		this.id = id;
		this.lable = lable;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLable() {
		return lable;
	}
	
	public void setLable(String lable) {
		this.lable = lable;
	}
	
	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}

	public Vertice getPai() {
		return pai;
	}

	public void setPai(Vertice pai) {
		this.pai = pai;
	}

	@Override
	public int compareTo(Vertice o) {
		if (this.chave < o.getChave())return -1;
		else if(this.chave > o.getChave()) return 1;
		return 0;
	}

	@Override
	public String toString() {
		return "Vertice [id=" + id + ", lable=" + lable
				+ ", pai=" + pai + "]";
	}

}
