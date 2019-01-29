package middleware;


import java.util.Collection;

import domain.IDescriptor;
import domain.IFunction;
import domain.Operation;
import domain.Property;

public interface IMiddlewareFacade {
	
	public Collection<IDescriptor> getDevices() throws MiddlewareException;
	
	public Collection<IFunction> getADeviceFunctions(IDescriptor desc) throws MiddlewareException;
	
	public Property getProperty(Property prop) throws MiddlewareException;
	
	public void executeOperation(Operation operation) throws MiddlewareException;

	public Collection<Property> updateProperties(IFunction state) throws MiddlewareException;

}
