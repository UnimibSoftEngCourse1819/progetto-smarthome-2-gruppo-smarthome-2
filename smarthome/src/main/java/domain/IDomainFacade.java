package domain;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public interface IDomainFacade {
	//TODO Il tipo di ritorno va definito capendo cosa serve alla GUI
	public void scanDevices() throws FileNotFoundException, IOException, ParseException, Exception;
	
	public void addDevice(Object id) throws FileNotFoundException, IOException, ParseException;

}
