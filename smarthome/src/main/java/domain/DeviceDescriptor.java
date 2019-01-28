package domain;


import java.util.Objects;

import javafx.util.*;

public class DeviceDescriptor implements IDescriptor{
	
	//Map<Tag,Object> descriptorElements;
	private javafx.util.Pair<Tag, Object> deviceId;
	private javafx.util.Pair<Tag, Object> deviceName;
	
	
	private DeviceDescriptor(IDescriptor descrittore) {
		this.deviceId  = new javafx.util.Pair<>(new TagDevice("UID"),descrittore.getId());
		this.deviceName = new javafx.util.Pair<>(new TagDevice("name"),descrittore.getName());	
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
			DeviceDescriptor deviceDescriptor = new DeviceDescriptor(abstractDesc);
			return deviceDescriptor;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "DeviceDescriptor [deviceId=" + deviceId + ", deviceName=" + deviceName + "]";
}

/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	return Objects.hash(deviceId, deviceName);
}

/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
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
