package domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.json.simple.parser.ParseException;

public interface IDomainFacade {
	//TODO Il tipo di ritorno va definito capendo cosa serve alla GUI
	public Collection<IDescriptor> scanDevices() throws FileNotFoundException, IOException, ParseException, Exception;
	

	public Device addDevice(DeviceDescriptor dvDesc) throws FileNotFoundException, IOException, ParseException;

}
