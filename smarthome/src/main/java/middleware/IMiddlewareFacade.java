package middleware;


import java.util.Collection;
import domain.IDescriptor;
import domain.IFunction;
import domain.Property;

public interface IMiddlewareFacade {
	
	Collection<IDescriptor> getDevices() throws MiddlewareException;
	
	//TODO SOLo per testare
	Collection<IFunction> getADeviceFunctions(IDescriptor desc) throws MiddlewareException;
	
	Property getProperty(Property prop) throws MiddlewareException;

}
