package adapters;


import org.json.simple.JSONObject;

import domain.IDescriptor;
import domain.TagDevice;


public class DescriptorAdapter implements IDescriptor {
	
	private JSONObject adaptee;	
	
	public DescriptorAdapter(JSONObject adaptee){
		this.adaptee = adaptee;
	}
	
	@Override
	public Object getId() {
		Object key = new TagDevice("UID").getTagValue();  // dal.device.UID
		 return  (this.adaptee.get(key));
	}
	
	@Override
	public Object getName() {
		Object key = new TagDevice("name").getTagValue();  // dal.device.name
		 return  (this.adaptee.get(key));
	}
	
	

}
