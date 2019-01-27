package domain;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import middleware.MiddlewareException;

public interface IDomainFacade {
	//TODO Il tipo di ritorno va definito capendo cosa serve alla GUI
	public void scanDevices() throws MiddlewareException;
	
	public void addDevice(Object id) throws MiddlewareException;

}
