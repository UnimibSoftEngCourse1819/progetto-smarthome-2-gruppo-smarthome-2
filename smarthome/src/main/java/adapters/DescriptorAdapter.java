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


/*
	public Object lookForAParameter(String param) {
		//System.out.println(param);
		//System.out.println(this.adaptee.get(param));
		return this.adaptee.get(param);
	}


	
	public Set<Tag> getDescriptorParameters() {
		Set<Tag> tagsForDescriptor = new HashSet<>();
		
		for (Object key : this.adaptee.keySet())
			tagsForDescriptor.add(new Tag(key.toString()));
		
		return tagsForDescriptor;
	}
	
	*/
	public String extractAProperty(String parameter) {
		String res = "";
		//Pattern di riconoscimento di Regex
		   Pattern pattern = Pattern.compile(parameter);
	
		    for(Object key : this.adaptee.keySet()){
		    	if(pattern.matcher(new String(key.toString())).find()){
		    		//System.out.println(key.toString());
		    		//System.out.println(this.adaptee.get(key).getClass());
		    		res = (String) this.adaptee.get(key);
		    		
		    	}
		    }
		    return res;
	}	

}
