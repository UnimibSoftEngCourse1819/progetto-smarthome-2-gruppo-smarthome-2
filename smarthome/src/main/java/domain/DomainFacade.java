package domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.simple.parser.ParseException;

import gui.GUIFacade;
import gui.IGUIFacade;
import middleware.IMiddlewareFacade;
import middleware.MiddlewareException;
import middleware.MiddlewareFacade;

public class DomainFacade implements IDomainFacade {
	
	private SmartHome home;
	
	
	/*Deve poter chiamare la facade di Middleware*/
	private IMiddlewareFacade middlewareFacade;
	
	
	public DomainFacade(){
		this.middlewareFacade = new MiddlewareFacade();
		this.home = SmartHome.getInstance();
	}
	
	@Override
	public Collection<DeviceDescriptor> scanDevices() throws MiddlewareException {	
		Collection<IDescriptor> descs = middlewareFacade.getDevices();
		this.home.createDeviceDescriptors(descs);
		return this.home.getDeviceDescriptors();
	}
	
	public List<DeviceDescriptor> getDeviceDescriptors() {
		return this.home.getDeviceDescriptors(); 
	}
	
	public Device addDevice(IDescriptor devDesc) throws MiddlewareException{
			System.out.println("Contains!");
			DeviceFactory fact = new DeviceFactory();
			fact.addDeviceDescriptor(devDesc);
			Collection<IFunction> adapters = 
					 this.middlewareFacade.getADeviceFunctions(devDesc);
			fact.addFunctions(adapters);
			return fact.getInstance();
	}


	public Collection<Device> getDevices() {
		return this.home.getDevices();
	}


}
