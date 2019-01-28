package adapters;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import domain.CommandFactory;
import domain.ICommand;
import domain.IFunction;
import domain.Pair;
import domain.Tag;
import domain.TagFunction;
import middleware.MiddlewareException;

public class FunctionAdapter implements IFunction {
	
	JSONObject adaptee;
	
	
	
	public FunctionAdapter(JSONObject obj){
		this.adaptee = obj;
	}
	
	private JSONArray findTag(String param){
		 Object key = this.extractAKey(param);
		 return (JSONArray) this.adaptee.get(key);
	}

	@Override
	public Object getId() {
		Object key = this.extractAKey("UID");
		 return  (this.adaptee.get(key));
	}

	@Override
	public Collection<ICommand> getCommands() {
		Collection<ICommand> res = new ArrayList<>();
		CommandFactory fact = new CommandFactory();
		JSONArray ops = this.findTag("operation");
		
		if(ops != null){
			for(Object op : ops){
			res.add(fact.createCommand(new TagFunction("operation.name"), 
					op, 
					new Pair(new TagFunction("UID"),this.getId())
					));
			}
		}
			
		 ops = this.findTag("property");
		 if(ops != null){
				for(Object op : ops)
					res.add(fact.createCommand(new TagFunction("property.name"), 
							op,
							new Pair(new TagFunction("UID"), this.getId())));	
		 }
		
		return res;
	}
	
	public Object extractAKey(String parameter) {
		Object res = new Object();
		//Pattern di riconoscimento di Regex
		   Pattern pattern = Pattern.compile(parameter);
	
		    for(Object key : this.adaptee.keySet()){
		    	if(pattern.matcher(new String(key.toString())).find()){
		    		//System.out.println(key.toString());
		    		//System.out.println(this.adaptee.get(key));
		    		res = key;
		    		
		    	}
		    }
		    return res;
	}
	
	public Set<Tag> getFunctionParameters() {
		Set<Tag> tagsForDescriptor = new HashSet<>();
		
		for (Object key : this.adaptee.keySet())
			tagsForDescriptor.add(new Tag(key.toString()));
		
		return tagsForDescriptor;
	}

	@Override
	public void callCommand(Object name) throws MiddlewareException {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
