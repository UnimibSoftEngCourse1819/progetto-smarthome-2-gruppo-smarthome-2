package domain;

public class Tag {
	
	private String root;
	private String parameter;
	
	
	public Tag(){
		this.root = "";
		this.parameter = "";
		}
	
	
	public Tag(String root){
		this.root = root;
		this.parameter="";
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parameter == null) ? 0 : parameter.hashCode());
		result = prime * result + ((root == null) ? 0 : root.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (parameter == null) {
			if (other.parameter != null)
				return false;
		} else if (!parameter.equals(other.parameter)) {
			return false;
		}	
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root)) {
			return false;
		}	
		return true;
	}

	@Override
	public String toString() {
		return "Tag [root=" + root + ", parameter=" + parameter + "]";
	}
	
	
	
	
}
