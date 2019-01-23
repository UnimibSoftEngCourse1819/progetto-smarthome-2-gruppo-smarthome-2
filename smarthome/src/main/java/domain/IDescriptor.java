package domain;

import java.util.Set;

public interface IDescriptor {
	
	public AbstractId getId();
	
	public Object lookForAParameter(String param);
	
	public Set<Tag> getDescriptorParameters();
	
	//public Object getDescription();
	
	//public String toString();
}
