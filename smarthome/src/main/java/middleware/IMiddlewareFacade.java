package middleware;


import java.util.Collection;
import java.util.List;

import domain.IDescriptor;
import domain.IFunction;
import domain.Operation;
import domain.Property;
import exceptions.MiddlewareException;

public interface IMiddlewareFacade {
	
	public Collection<IDescriptor> getDevices() throws MiddlewareException;
	
	public Collection<IFunction> getADeviceFunctions(IDescriptor desc) throws MiddlewareException;
	
	public Property getProperty(Property prop) throws MiddlewareException;
	
	public void executeOperation(Operation operation) throws MiddlewareException;

	public Collection<Property> updateProperties(IFunction state) throws MiddlewareException;
	
	public Collection<IDescriptor> getSavedDevices() throws MiddlewareException;
	
	public void saveDevice(List<IDescriptor> desc) throws MiddlewareException;

	


}
