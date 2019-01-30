package domain;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.junit.Test;

import middleware.MiddlewareException;
import middleware.MiddlewareFacade;

public class DeviceTests {
	
	MiddlewareFacade middlewareFacade = new MiddlewareFacade();
	DomainFacade domainFacade = new DomainFacade();
	
	
	// public Collection<Idescr>
	// URL url = getClass().getResource("devices.json");
	// this.middlewareFacade.getDevices(new File(url.getPath())); 
	
	@Test
	public void testConstructor() throws MiddlewareException {
		Device device = new Device();
		assertTrue(device.getDescriptor()== null);
		assertTrue(device.getFunctions().size()==0);
		assertTrue(device.getState().getCurrentState().size()==0);
	}
}
