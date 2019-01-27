package domain;

public class Tag {
	
	private String root;
	private String parameter;
	
	
	public Tag(){};
	
	public Tag(String root){
		this.root = root;
		//this.parameter = parameter;
	}


	public String getTagValue() {
		return this.root + this.parameter;
	}


	public String getRoot() {
		return root;
	}


	public void setRoot(String root) {
		this.root = root;
	}


	public String getParameter() {
		return parameter;
	}


	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	


	
	
	

}
