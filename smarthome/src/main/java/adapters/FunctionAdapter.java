package adapters;


import java.util.ArrayList;
import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import domain.CommandFactory;
import domain.ICommand;
import domain.IFunction;
import domain.Operation;
import domain.Property;
import domain.TagFunction;
import exceptions.NoOperationException;
import javafx.util.Pair;
import middleware.MiddlewareException;

public class FunctionAdapter implements IFunction {
	
	private JSONObject adaptee;
	
	
	
	public FunctionAdapter(JSONObject obj){
		this.adaptee = obj;
	}
	

	@Override
	public Object getId() {
		Object key = new TagFunction("UID").getTagValue();
		 return  (this.adaptee.get(key));
	}

	@Override
	public Collection<ICommand> getCommands() {
		Collection<ICommand> res = new ArrayList<>();
		CommandFactory fact = new CommandFactory();
		createOperations(res, fact);
		createProperties(res, fact);
		return res;
	}


	private void createProperties(Collection<ICommand> res, CommandFactory fact) {
		JSONArray ops = this.findParam("property");
		 if(ops != null){
				for(Object op : ops)
					res.add(fact.createCommand(new TagFunction("property.name"), 
							op,
							new Pair(new TagFunction("UID"), this.getId())));	
		 }
	}


	private void createOperations(Collection<ICommand> res, CommandFactory fact) {
		JSONArray ops = this.findParam("operation");
		if(ops != null){
			for(Object op : ops){
			res.add(fact.createCommand(new TagFunction("operation.name"), 
					op, 
					new Pair(new TagFunction("UID"),this.getId())
					));
			}
		}
	}
	
	private JSONArray findParam(String param){
		TagFunction key = new TagFunction(param + ".names");
		 return (JSONArray) this.adaptee.get(key.getTagValue());
	}
	


	@Override
	public void callCommand(Object name) throws MiddlewareException {
		// unsupported from adapter..
	}


	@Override
	public Collection<Operation> getOperations() throws NoOperationException {
		return new ArrayList<Operation>();
	}


	@Override
	public Collection<Property> getProperties() {
		return new ArrayList<Property>();
	}
	
	
	

}
