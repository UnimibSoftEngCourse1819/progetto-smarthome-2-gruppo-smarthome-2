package gui;

import java.util.Collection;

import domain.Device;
import domain.DeviceDescriptor;

public interface IGUIFacade {
	public void show();
	public void show(Collection <DeviceDescriptor> descs);
	public void showDevice(Device d);
}
