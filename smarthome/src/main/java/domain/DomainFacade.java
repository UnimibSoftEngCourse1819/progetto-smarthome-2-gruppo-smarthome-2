package domain;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

import exceptions.AlreadyInCacheException;
import middleware.IMiddlewareFacade;
import middleware.MiddlewareException;
import middleware.MiddlewareFacade;
import persistance.PersistanceController;

public class DomainFacade implements IDomainFacade {
	
	private SmartHome home;
	
	private IMiddlewareFacade middlewareFacade;
	
	PersistanceController db;
	
	
	public DomainFacade(){
		this.middlewareFacade = new MiddlewareFacade();
		this.home = SmartHome.getInstance();
		try {
			this.db = new PersistanceController();
			this.initSavedDevices();
		} catch (IOException | MiddlewareException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Collection<DeviceDescriptor> scanDevices() throws MiddlewareException {
		try{
		Collection<IDescriptor> descs = middlewareFacade.getDevices();
		this.home.createDeviceDescriptors(descs);
		this.home.cleanAlreadyAdded();
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
	
	public IDevice addDevice(IDescriptor devDesc) throws MiddlewareException{
		DeviceFactory fact = new DeviceFactory();
		fact.addDeviceDescriptor(devDesc);
		try {
			db.saveToFile(devDesc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collection<IFunction> adapters = 
				this.middlewareFacade.getADeviceFunctions(devDesc);
		fact.addFunctions(adapters);
		this.home.deleteDeviceDescriptor(devDesc);
		this.home.addToMyDevices(fact.getInstance());
		fact.getInstance().initState();
		return fact.getInstance();
	}
	
	@Override
	public void callCommand(Object deviceId, Object idfunct, Object idcommand) throws MiddlewareException{
		this.home.devices.get(deviceId.toString()).callFunctionCommand(idfunct, idcommand);
	}

	public void initSavedDevices() throws IOException, MiddlewareException{
		for(IDescriptor devdesc : db.convertToIDescriptors())
			this.addDevice(devdesc);
	}
	
	public Collection<Device> getDevices() {
		return this.home.getDevices();
	}


}
