package domain;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

import exceptions.AlreadyInCacheException;
import exceptions.MiddlewareException;
import middleware.IMiddlewareFacade;
import middleware.MiddlewareFacade;
import persistance.PersistanceController;

public class DomainFacade implements IDomainFacade {
	
	private SmartHome home;
	private static final String DOMAINLOGGER = "domainLogger";
	private IMiddlewareFacade middlewareFacade;
	
	
	
	public DomainFacade() throws MiddlewareException{
		try {
		this.home = SmartHome.getInstance();
		this.middlewareFacade = new MiddlewareFacade();
		this.initSavedDevices();
		} catch (MiddlewareException e) {
			java.util.logging.Logger.getLogger(DOMAINLOGGER).log(Level.WARNING,e.getMessage(), e);
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
				java.util.logging.Logger.getLogger(DOMAINLOGGER).log(Level.WARNING,e.getMessage(), e);

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
			this.middlewareFacade.saveDevice(devDesc);  // chiamata al middle..dove passi un IDesc
		} catch (MiddlewareException | IOException e) {
			java.util.logging.Logger.getLogger(DOMAINLOGGER).log(Level.WARNING,e.getMessage(), e);
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

	public void initSavedDevices() throws MiddlewareException{
		for(IDescriptor devdesc : this.middlewareFacade.getSavedDevices()) // una chiamata a middle
			// getsavedDevices che torna una lista di idescriptor basata su descriptor adapter..
			this.addDevice(devdesc);
	}
	
	public Collection<Device> getDevices() {
		return this.home.getDevices();
	}


}
