package br.ufpb.so.pageReplacement;

/**
 * @author Joao Helis Bernardo
 *
 */
public class Page<E>{
	
	private int id;
	
	private Object content;
	
	public Page(int id){
		this(id, null);
	}

	public Page(int id, E content){
		super();
		this.id = id;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Object getContent() {		
		return content;
	}

	@Override
	public String toString() {
		return "Page [id=" + id + "]";//", content=" + content + "]";
	}
}
