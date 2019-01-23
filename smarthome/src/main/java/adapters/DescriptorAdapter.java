package adapters;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;

import domain.AbstractId;
import domain.DeviceId;
import domain.IDescriptor;
import domain.Tag;

public class DescriptorAdapter implements IDescriptor {
	
	private JSONObject adaptee;
	
	private static final String id = "UID";
	
	
	
	
	public DescriptorAdapter(JSONObject adaptee){
		this.adaptee = adaptee;
	}
	

	@Override
	public AbstractId getId() {
		return new DeviceId(this.extractAProperty(id).toString());
	}


	@Override
	public Object lookForAParameter(String param) {
		//System.out.println(param);
		//System.out.println(this.adaptee.get(param));
		return this.adaptee.get(param);
	}


	@Override
	public Set<Tag> getDescriptorParameters() {
		Set<Tag> tagsForDescriptor = new HashSet<>();
		
		for (Object key : this.adaptee.keySet())
			tagsForDescriptor.add(new Tag(key.toString()));
		
		return tagsForDescriptor;
	}
	
	
	public JSONObject extractAProperty(String parameter) {
		JSONObject res = new JSONObject();
		//Pattern di riconoscimento di Regex
		   Pattern pattern = Pattern.compile(parameter);
	
		    for(Object key : this.adaptee.keySet()){
		    	if(pattern.matcher(new String(key.toString())).find()){
		    		System.out.println(key.toString());
		    		System.out.println(this.adaptee.get(key));
		    		res = (JSONObject) this.adaptee.get(key);
		    		
		    	}
		    }
		    return res;
	}
	
	
	

}
