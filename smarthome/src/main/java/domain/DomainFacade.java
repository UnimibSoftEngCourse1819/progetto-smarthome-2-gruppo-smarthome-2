package domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.simple.parser.ParseException;

import middleware.IMiddlewareFacade;
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
	public void scanDevices() throws FileNotFoundException, IOException, ParseException, Exception {	
		Collection<IDescriptor> descs = middlewareFacade.getDevices();
		this.home.createDeviceDescriptors(descs);
	}
	
	
	public List<DeviceDescriptor> getDeviceDescriptors() { return this.home.getDeviceDescriptors(); }
	
	
	public void addDevice(Object devDesc) throws FileNotFoundException, IOException, ParseException{
		if(this.home.getDeviceDescriptors().contains(devDesc)){
			//int indx = this.getDeviceDescriptors()
			System.out.println("COntains!");
			DeviceFactory fact = new DeviceFactory();
			//fact.addDeviceDescriptor(this.home.getDeviceDescriptors().);
			//DeviceBuilder dvb = new DeviceBuilder();
			//dvb.addChild(this.home.getDeviceDescriptors().get(3));
			Collection<IFunction> ans = 
					 this.middlewareFacade.getADeviceFunctions(this.home.getDeviceDescriptors().get(2));
			for(IFunction f : ans){
				System.out.println("FUNCTION ID : " + f.getId());
				System.out.println("COMMAND: " + f.getCommands() );
			}
			//this.home.addDevice(dvb.getDevice());
		}
	}


	public Collection<Device> getDevices() {
		return this.home.getDevices();
	}


}
