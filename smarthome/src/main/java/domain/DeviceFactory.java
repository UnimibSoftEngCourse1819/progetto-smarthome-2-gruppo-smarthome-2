package domain;

public class DeviceFactory {
	
	private Device dev = new Device();
	
	
	public void addDeviceDescriptor(IDescriptor desc){
		this.dev.setDescriptor(desc);
	}
	
	public void addFunctions(IDescriptor desc){
		
	}
	
	

}
