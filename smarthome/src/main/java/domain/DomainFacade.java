package domain;

import java.io.IOException;
import java.util.ArrayList;
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
		System.out.println(devDesc.getClass());
		fact.addDeviceDescriptor(devDesc);
		
		/* try {
			this.middlewareFacade.saveDevice(devDesc);  // chiamata al middle..dove passi un IDesc
		} catch (MiddlewareException | IOException e) {
			java.util.logging.Logger.getLogger(DOMAINLOGGER).log(Level.WARNING,e.getMessage(), e);
		} */
		
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
		Collection<IDescriptor> descs = this.middlewareFacade.getSavedDevices();
			//this.home.createDeviceDescriptors(descs);
		for(IDescriptor devdesc : descs) { // una chiamata a middle
			// getsavedDevices che torna una lista di idescriptor basata su descriptor adapter..
			System.out.println(devdesc.getName());
			
			this.addDevice(DeviceDescriptor.createDeviceDescriptor(devdesc.getId(), devdesc.getName()));
		}
	}
	
	public Collection<Device> getDevices() {
		return this.home.getDevices();
	}

	public void saveMyDevices() throws MiddlewareException, IOException {
		List <IDescriptor> dd = new ArrayList<>();
		for (Device dev : this.home.getDevices()) {
			dd.add(dev.getDescriptor());
		}
           this.middlewareFacade.saveDevice(dd);	
	}

	public void removeDevice(Device device) {
		this.home.removeFromMyDevices(device);
	}


}
