package domain;

import java.util.Collection;

public class DeviceFactory {
	
	private Device dev;
	
	public DeviceFactory(){
		this.dev = new Device();
	}
	
	
	public void addDeviceDescriptor(IDescriptor desc){
		this.dev.setDescriptor(desc);
	}
	
	public void addFunctions(Collection<IFunction> adapt){
		for(IFunction f : adapt){
			Function funct = new Function(f.getId().toString()); 
			funct.setCommands(f.getCommands());
			this.dev.addFunction(funct);
		}
	}

	public Device getInstance() {
		return this.dev;
	}
	
	

}
