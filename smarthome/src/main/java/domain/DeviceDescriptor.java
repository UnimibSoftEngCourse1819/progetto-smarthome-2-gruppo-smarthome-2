package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class DeviceDescriptor implements IDescriptor{
	
	Map<Tag,Object> descriptorElements;
	
	
	private DeviceDescriptor() {
		descriptorElements = new HashMap<>();
	}
	
	public void addElement(Tag t, Object value){
		this.descriptorElements.put(t, value);
	}
	
	
	/** GETTERS
	 * @return the id
	 */
	public DeviceId getId() {
		
		return new DeviceId(this.lookForAParameter("UID").toString());
	}
	
	
	
	/**
	 * @return the description
	 */
	public String getDeviceName() {
		return this.lookForAParameter("name").toString();
	}
	
	

	@Override
	public Object lookForAParameter(String param) {
		
		//Con questo metodo come in LowObject cerchi una corrispondenza parziale
		//COn le Espressioni regolari
		Pattern p = Pattern.compile(param);
		for(Tag t : this.descriptorElements.keySet())
			   if(p.matcher(t.toString()).find())
				   return this.descriptorElements.get(t);
		return null;
	}


 public String toString(){
	 return this.descriptorElements.toString();
 }
 	
 	
 

static class DeviceDescriptorFactory{
			
		public static DeviceDescriptor createDeviceDescriptor(IDescriptor lowObject)
			{
			DeviceDescriptor deviceDescriptor = new DeviceDescriptor();
			deviceDescriptor.initializeDescriptor(lowObject);
			return deviceDescriptor;
			}
			
		}

public void initializeDescriptor(IDescriptor abstractDesc) {
	Set<Tag> tags = abstractDesc.getDescriptorParameters();
	for(Tag t : tags)
		this.descriptorElements.put(t, abstractDesc.lookForAParameter(t.toString()));
}


public Set<Tag> getDescriptorParameters() {
	
	return this.descriptorElements.keySet();
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((descriptorElements == null) ? 0 : descriptorElements.hashCode());
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
	DeviceDescriptor other = (DeviceDescriptor) obj;
	if (descriptorElements == null) {
		if (other.descriptorElements != null)
			return false;
	} else if (!descriptorElements.equals(other.descriptorElements))
		return false;
	return true;
}





}
