package domain;

public class Tag {
	
	private String tagValue;
	
	
	public Tag(String value){
		this.tagValue = value;
	}


	public String getTagValue() {
		return tagValue;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tagValue == null) ? 0 : tagValue.hashCode());
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
		if (tagValue == null) {
			if (other.tagValue != null)
				return false;
		} else if (!tagValue.equals(other.tagValue))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return tagValue;
	}
	
	
	

}
