package gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.json.simple.parser.ParseException;

import domain.Device;
import domain.DeviceDescriptor;
import domain.IDescriptor;
import domain.IFunction;
import middleware.MiddlewareException;

public interface IGUIFacade {
	public void show();
	public void show(Collection <DeviceDescriptor> descs);
	public void showDevice(Device d);
}
