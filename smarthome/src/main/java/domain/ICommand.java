package domain;

import exceptions.MiddlewareException;

public interface ICommand {
	
	public void execute() throws MiddlewareException;
	
	public Tag getTag();
	
	public Object getName();

	public Object getFunctionId();
}
