package domain;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SmartHome {

	
	List<DeviceDescriptor> devDesc;
	Map<String,Device> devices;

	
	
	private static final SmartHome INSTANCE = new SmartHome();


	private SmartHome(){
		this.devDesc = new ArrayList<DeviceDescriptor>();
		this.devices = new HashMap<String, Device>();
	}

	public static SmartHome getInstance(){
		return INSTANCE;
	}

	public List<DeviceDescriptor> getDeviceDescriptors() {
		return this.devDesc;
		}

	public void createDeviceDescriptors(Collection<IDescriptor> descs) {
			for (IDescriptor element : descs)
				devDesc.add(DeviceDescriptor.createDeviceDescriptor(element));
	}
	
	public void deleteDeviceDescriptor(IDescriptor devDesc){
		this.devDesc.remove(devDesc);
	}
		
	Collection<Device> getDevices() { return this.devices.values(); }

	public void addToMyDevices(Device device) {
		this.devices.put(device.getDescriptor().getId().toString(), device);
	}
	
	public void cleanAlreadyAdded(){
		System.out.println(this.devices.values());
		ArrayList<DeviceDescriptor> toDelete = new ArrayList<>();
		for(Device device : this.devices.values()) {
			for (DeviceDescriptor dd : devDesc) {
				if (device.getDescriptor().getId().equals(dd.getId())) {
					toDelete.add(dd);
				}
						
			}
		}
		
		for (DeviceDescriptor dd : toDelete) {
			this.devDesc.remove(dd);
			
		}
	}

	public void removeFromMyDevices(Device device) {
		this.devices.remove(device.getDescriptor().getId().toString());
	System.out.println(device.getDescriptor().getClass());
		this.devDesc.add((DeviceDescriptor) device.getDescriptor());		
	}

}
