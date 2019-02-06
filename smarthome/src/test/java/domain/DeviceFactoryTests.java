package domain;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import exceptions.MiddlewareException;
import middleware.MiddlewareFacade;
import middleware.MiddlewareFacadeTest;

public class DeviceFactoryTests {
	
	MiddlewareFacadeTest middlewareFacade = new MiddlewareFacadeTest();
	
	public <T> boolean listEqualsIgnoreOrder(List<T> list1, List<T> list2) {
	    return (new HashSet<>(list1).equals(new HashSet<>(list2)));
	}
	
	public Collection<IDescriptor> getDeviceDescriptor() throws MiddlewareException{
		URL url = getClass().getResource("devices.json");
		return this.middlewareFacade.getDevicesTest(new File(url.getPath())); 
	}
	
	public File fileFunction() throws MiddlewareException{
		URL url = getClass().getResource("function_doorLock.txt");
		return new File(url.getPath());
		}
	
	@Test
	public void testConstructor() throws MiddlewareException {
		DeviceFactory deviceFactory = new DeviceFactory();
		assertTrue(deviceFactory.getInstance().getDescriptor()== null);
		assertTrue(deviceFactory.getInstance().getFunctions().size()==0);
		assertTrue(deviceFactory.getInstance().getState().getCurrentState().size()==0);
	}
	
	@Test
	public void testaddDeviceDescriptor() throws MiddlewareException{
		List<IDescriptor> listDesc = (ArrayList<IDescriptor>) this.getDeviceDescriptor();
		DeviceFactory deviceFactory = new DeviceFactory();
		deviceFactory.addDeviceDescriptor(listDesc.get(0)); // doorLock
		assertTrue(deviceFactory.getInstance().getDescriptor().equals(listDesc.get(0)));
	}
	
	@Test
	public void testAddFunctions() throws MiddlewareException {
		List<IDescriptor> listDesc = (ArrayList<IDescriptor>) this.getDeviceDescriptor();
		DeviceFactory deviceFactory = new DeviceFactory();
		deviceFactory.addDeviceDescriptor(listDesc.get(0)); // elemento doorLook
		ArrayList<IFunction> listFunctAdapters = (ArrayList<IFunction>)this.middlewareFacade.getADeviceFunctionsTest(deviceFactory.getInstance().getDescriptor(),this.fileFunction());
		deviceFactory.addFunctions(listFunctAdapters);
		List<IFunction> functions = new ArrayList<>();
		for(IFunction f : listFunctAdapters){
			Function funct = new Function(f.getId().toString()); 
			funct.setCommands(f.getCommands());
			functions.add(funct);
		}
		assertTrue(this.listEqualsIgnoreOrder((List)deviceFactory.getInstance().getFunctions(), functions));
	}
}
