package gui;

import java.util.Collection;

import domain.DeviceDescriptor;
import domain.IDevice;

public interface IGUIFacade {
	public void show();
	public void show(Collection <DeviceDescriptor> descs);
	public void showDevice(IDevice d);
	public void showSmartHome();
	
}
