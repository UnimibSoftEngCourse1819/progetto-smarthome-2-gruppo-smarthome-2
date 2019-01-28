package domain;

import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;
import middleware.IMiddlewareFacade;
import middleware.MiddlewareException;
import middleware.MiddlewareFacade;

public class Property implements ICommand {
	
	Map<Object,Object> parameters;
	IMiddlewareFacade receiver;
	//TODO FORSE PROPERTY E OP POSSONO ESTENDERE UNA CLASSE ASTRATTA
	//ABSTRACTCOMMAND
	
	Pair<Tag, Object> funId;
	Tag type;
	Object name;
	
	public Property(Tag t, Object n,Pair fId){
		this.type = t;
		this.name = n;
		this.funId = fId;
		this.parameters = new HashMap<>();
		this.receiver = new MiddlewareFacade();
	}
	

	@Override
	public void execute() throws MiddlewareException {
		Property result = this.receiver.getProperty(this);
		System.out.println(result.getParameters());
		//Setta i risultati
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
