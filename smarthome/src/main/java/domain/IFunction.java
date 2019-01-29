package domain;

import java.util.Collection;

import exceptions.NoOperationException;
import middleware.MiddlewareException;

public interface IFunction {
	
	public Object getId();
	
	public Collection<ICommand> getCommands();
	
	public Collection<Operation> getOperations() throws NoOperationException;
	
	public Collection<Property> getProperties();
	
	public void callCommand(Object name) throws MiddlewareException;


}
