package domain;

import middleware.MiddlewareException;

public interface ICommand {
	
	public void execute() throws MiddlewareException;
	
	public Tag getTag();
	
	public Object getName();

}
