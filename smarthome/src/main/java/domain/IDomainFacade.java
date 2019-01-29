package domain;

import java.util.Collection;

import middleware.MiddlewareException;

public interface IDomainFacade {

	public Collection<DeviceDescriptor> scanDevices() throws MiddlewareException;
	
	public Device addDevice(IDescriptor id) throws MiddlewareException;
}
