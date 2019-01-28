package adapters;


import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import domain.IDescriptor;
import domain.TagDevice;
import domain.TagFunction;


public class DescriptorAdapter implements IDescriptor {
	
	private JSONObject adaptee;	
	//private static final String id = "UID";
	//private static final String name = "name";
	
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
		 //Object key = this.extractAKey(param);
		TagDevice key = new TagDevice(param);
		//System.out.println(key.getTagValue());
		 return this.adaptee.get(key.getTagValue());
	}
	
	
	
	
	/*
	public String extractAProperty(String parameter) {
		String res = "";

		//Pattern di riconoscimento di Regex
		   Pattern pattern = Pattern.compile(parameter);
		    for(Object key : this.adaptee.keySet()){
		    	if(pattern.matcher(new String(key.toString())).find()){

		    		
		    		res = (String) this.adaptee.get(key);
		    		
		    	}
		    }
		    return res;
	}	*/


}
