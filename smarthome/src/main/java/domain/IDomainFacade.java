package domain;

import java.util.Collection;

import middleware.MiddlewareException;

public interface IDomainFacade {

	public Collection<DeviceDescriptor> scanDevices() throws MiddlewareException;
	
	public IDevice addDevice(IDescriptor id) throws MiddlewareException;
	
	public void callCommand(Object deviceId, Object idfunct, Object idcommand) throws MiddlewareException;
}
