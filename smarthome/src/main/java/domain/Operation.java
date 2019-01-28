package domain;

import middleware.IMiddlewareFacade;
import middleware.MiddlewareException;
import middleware.MiddlewareFacade;

public class Operation implements ICommand {
	
	Pair funId;
	
	Tag type;
	Object name;
	IMiddlewareFacade receiver;
	
	public Operation(Tag t, Object n,Pair p){
		this.type = t;
		this.name = n;
		this.funId = p;
		this.receiver = new MiddlewareFacade();
	}
	

	@Override
	public void execute() throws MiddlewareException {
		this.receiver.executeOperation(this); // andare in eccezione	
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
