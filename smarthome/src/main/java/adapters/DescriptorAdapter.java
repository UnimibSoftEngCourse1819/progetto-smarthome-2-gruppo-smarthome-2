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
		return this.findParam("UID");
	}
	
	@Override
	public Object getName() {
		return this.findParam("name");
	}
	
	
	private Object findParam(String param){
		TagDevice key = new TagDevice(param);
		 return this.adaptee.get(key.getTagValue());
	}

}
