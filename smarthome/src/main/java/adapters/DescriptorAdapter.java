package adapters;


import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import domain.IDescriptor;


public class DescriptorAdapter implements IDescriptor {
	
	private JSONObject adaptee;	
	private static final String id = "UID";
	private static final String name = "name";
	
	public DescriptorAdapter(JSONObject adaptee){
		this.adaptee = adaptee;
	}
	
	@Override
	public Object getId() {
		return this.extractAProperty(id);
	}
	
	@Override
	public Object getName() {
		return this.extractAProperty(name);
	}

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
	}	


}
