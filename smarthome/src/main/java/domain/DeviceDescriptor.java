package domain;




public class DeviceDescriptor implements IDescriptor{
	
	//Map<Tag,Object> descriptorElements;
	
	Pair deviceId;
	Pair deviceName;
	
	private DeviceDescriptor() {
		this.deviceId = new Pair();
		//this.deviceId.setKey(new TagDevice("UID"));
		this.deviceName = new Pair();
		//this.deviceName.setKey(new TagDevice("name"));
	}
	
	
	
	/** GETTERS
	 * @return the id
	 */
	public Object getId() {
		
		return this.deviceId.getValue();
	}
	
	
	
	/**
	 * @return the description
	 */
	public Object getName() {
		return this.deviceName.getValue();
	}
	
			
public static DeviceDescriptor createDeviceDescriptor(IDescriptor abstractDesc){
			DeviceDescriptor deviceDescriptor = new DeviceDescriptor();
			deviceDescriptor.initializeDescriptor(abstractDesc);
			return deviceDescriptor;
}
			
		

private void initializeDescriptor(IDescriptor abstractDesc) {
	this.deviceId.setKey(new TagDevice("UID"));
	this.deviceName.setKey(new TagDevice("name"));
	this.deviceId.setValue(abstractDesc.getId());
	this.deviceName.setValue(abstractDesc.getName());
	
}


}
