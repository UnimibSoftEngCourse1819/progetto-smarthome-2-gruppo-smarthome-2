package domain;


import java.util.Objects;

public class DeviceDescriptor implements IDescriptor{
	
	private javafx.util.Pair<Tag, Object> deviceId;
	private javafx.util.Pair<Tag, Object> deviceName;
	
	
	private DeviceDescriptor(IDescriptor descrittore) {
		this.deviceId  = new javafx.util.Pair<>(new TagDevice("UID"),descrittore.getId());
		this.deviceName = new javafx.util.Pair<>(new TagDevice("name"),descrittore.getName());	
	}
	
	private DeviceDescriptor(Object devId, Object devName){
		this.deviceId  = new javafx.util.Pair<>(new TagDevice("UID"),devId);
		this.deviceName = new javafx.util.Pair<>(new TagDevice("name"),devName);	
	}
	
	
	public Object getId() {
		return this.deviceId.getValue();
	}
	
	
	public Object getName() {
		return this.deviceName.getValue();
	}
	
			
public static DeviceDescriptor createDeviceDescriptor(IDescriptor abstractDesc){
	return new DeviceDescriptor(abstractDesc);	
}

public static DeviceDescriptor createDeviceDescriptor(Object devId, Object devName){
	return new DeviceDescriptor(devId, devName);	
}

@Override
public String toString() {
	return "DeviceDescriptor [deviceId=" + deviceId + ", deviceName=" + deviceName + "]";
}


@Override
public int hashCode() {
	return Objects.hash(deviceId, deviceName);
}


@Override
public boolean equals(Object obj) {
	if (this == obj) {
		return true;
	}
	if (obj == null) {
		return false;
	}
	if (!(obj instanceof DeviceDescriptor)) {
		return false;
	}
	DeviceDescriptor other = (DeviceDescriptor) obj;
	return Objects.equals(deviceId, other.deviceId) && Objects.equals(deviceName, other.deviceName);
}
		

}
