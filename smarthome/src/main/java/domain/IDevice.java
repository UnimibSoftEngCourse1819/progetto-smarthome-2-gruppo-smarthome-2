package domain;

import java.util.Collection;
import java.util.Map;

import exceptions.MiddlewareException;
import exceptions.NoOperationException;

public interface IDevice {
	
	public void callFunctionCommand(Object idfunct, Object idcommand) throws MiddlewareException;
	
	public IDescriptor getDescriptor();
	
	public Collection<Object> getFunctionsIds();
	
	public Map<Object,Object> getAttributeOfAProperty(Object funId, Object propertyName);
	
	public Collection<Map<Object,Object>> getParametersOfThisFunction(Object funId);
	
	public Collection<Operation> getOperations() throws NoOperationException;
	
	public Collection<Property> getProperties();
	
	public void initState() throws MiddlewareException;
	
	
}
