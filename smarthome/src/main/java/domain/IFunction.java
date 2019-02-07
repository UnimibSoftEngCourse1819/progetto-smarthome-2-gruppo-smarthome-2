package domain;

import java.util.Collection;

import exceptions.MiddlewareException;
import exceptions.NoOperationException;

public interface IFunction {
	
	public Object getId();
	
	public Collection<ICommand> getCommands() throws MiddlewareException;
	
	public Collection<Operation> getOperations() throws NoOperationException;
	
	public Collection<Property> getProperties();
	
	public void callCommand(Object name) throws MiddlewareException;


}
