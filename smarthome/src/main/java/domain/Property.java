package domain;

import java.util.HashMap;
import java.util.Map;

import middleware.IMiddlewareFacade;
import middleware.MiddlewareException;
import middleware.MiddlewareFacade;

public class Property implements ICommand {
	
	Map<Object,Object> parameters;
	IMiddlewareFacade receiver;
	//TODO FORSE PROPERTY E OP POSSONO ESTENDERE UNA CLASSE ASTRATTA
	//ABSTRACTCOMMAND
	
	Pair funId;
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

}
