package domain;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

import middleware.AlreadyInCacheException;
import middleware.IMiddlewareFacade;
import middleware.MiddlewareException;
import middleware.MiddlewareFacade;

public class DomainFacade implements IDomainFacade {
	
	private SmartHome home;
	
	private IMiddlewareFacade middlewareFacade;
	
	
	public DomainFacade(){
		this.middlewareFacade = new MiddlewareFacade();
		this.home = SmartHome.getInstance();
	}
	
	@Override
	public Collection<DeviceDescriptor> scanDevices() throws MiddlewareException {
		try{
		Collection<IDescriptor> descs = middlewareFacade.getDevices();
		this.home.createDeviceDescriptors(descs);
		return this.home.getDeviceDescriptors();
		}
		catch(MiddlewareException e){
			if(e.getLowLevelException() instanceof AlreadyInCacheException)
				java.util.logging.Logger.getLogger("domainLogger").log(Level.WARNING,e.getMessage(), e);

			return this.home.getDeviceDescriptors();
		}

	}
	
	public List<DeviceDescriptor> getDeviceDescriptors() {
		return this.home.getDeviceDescriptors(); 
	}
	
	public Device addDevice(IDescriptor devDesc) throws MiddlewareException{
		DeviceFactory fact = new DeviceFactory();
		fact.addDeviceDescriptor(devDesc);
		Collection<IFunction> adapters = 
				this.middlewareFacade.getADeviceFunctions(devDesc);
		fact.addFunctions(adapters);
		this.home.deleteDeviceDescriptor(devDesc);
		this.home.addToMyDevices(fact.getInstance());
		return fact.getInstance();
	}


	public Collection<Device> getDevices() {
		return this.home.getDevices();
	}


}
