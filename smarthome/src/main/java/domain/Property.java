package domain;

import java.util.HashMap;
import java.util.Map;

import exceptions.MiddlewareException;
import javafx.util.Pair;
import middleware.IMiddlewareFacade;
import middleware.MiddlewareFacade;

public class Property implements ICommand {
	
	private Map<Object,Object> parameters;
	private IMiddlewareFacade receiver;
	private Pair<Tag, Object> funId;
	private Tag type;
	private Object name;
	
	public Property(Tag t, Object n,Pair fId) throws MiddlewareException{
		this.type = t;
		this.name = n;
		this.funId = fId;
		this.parameters = new HashMap<>();
		this.receiver = new MiddlewareFacade();
	}
	

	@Override
	public void execute() throws MiddlewareException {
		 this.receiver.getProperty(this);
	}


	@Override
	public Tag getTag() {
		return this.type;
	}

	public Object getName() {
		return this.name;
	}


	public Object getFunctionId() {
		return this.funId.getValue();
	}
	
	public void clear() {
		this.parameters.clear();
	}
	
	public void addParameter(Object key, Object value) {
		this.parameters.put(key, value);
	}
	
	public Map<Object, Object> getParameters() {
		return this.parameters;
	}

	@Override
	public String toString() {
		return "Property [parameters=" + parameters + ", name=" + name + "]";
	}
	
}
