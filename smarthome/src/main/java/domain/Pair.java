package domain;

public class Pair {
	
	private Tag key;
	private Object value;
	
	
	public Pair(){}
	
	public Pair(Tag key, Object value){
		this.key = key;
		this.value = value;
	}


	public Pair(Object key, Object val) {
		this.key = key;
		this.value = val;
	}

	public Tag getKey() {
		return key;
	}


	public void setKey(Tag key) {
		this.key = key;
	}


	public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}
	
	

}
