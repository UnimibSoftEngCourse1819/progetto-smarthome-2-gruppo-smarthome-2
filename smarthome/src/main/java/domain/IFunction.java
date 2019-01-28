package domain;

import java.util.Collection;

import middleware.MiddlewareException;

public interface IFunction {
	
	//TODO void call(ICommand com)
	public Object getId();
	
	public Collection<ICommand> getCommands();
	
	public void callCommand(Object name) throws MiddlewareException;


}
