package domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.json.simple.parser.ParseException;

import middleware.MiddlewareException;

public interface IDomainFacade {
	//TODO Il tipo di ritorno va definito capendo cosa serve alla GUI

	public Collection<DeviceDescriptor> scanDevices() throws MiddlewareException;
	
	public Device addDevice(IDescriptor id) throws MiddlewareException;
}
