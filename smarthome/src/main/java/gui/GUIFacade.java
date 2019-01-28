package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import domain.Device;
import domain.DeviceDescriptor;
import domain.DomainFacade;
import domain.ICommand;
import domain.IDescriptor;
import domain.IDevice;
import domain.IFunction;
import domain.SmartHome;
import middleware.MiddlewareException;
import middleware.MiddlewareFacade;

public class GUIFacade implements IGUIFacade {
	private DomainFacade domainFacade;
	
	public GUIFacade(){
		this.domainFacade = new DomainFacade();
	}
	
	@Override
	public void show() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeGUI home = new HomeGUI();
					
					JPanel p = new JPanel();
					p.add(home);
					home.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void show (Collection <IDescriptor> descs) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScanDeviceGUI scanPage = new ScanDeviceGUI(descs);
					
					JPanel p = new JPanel();
					p.add(scanPage);
					scanPage.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void showDevice (Device device) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeviceGUI devicePage = new DeviceGUI(device);
					
					JPanel p = new JPanel();
					p.add(devicePage);
					devicePage.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void showResult(Device device,Map<Object, Object> state) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					StateGUI statePage = new StateGUI(device, state);
					JPanel p = new JPanel();
					p.add(statePage);
					statePage.frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public Collection<IDescriptor> scan() throws MiddlewareException {
			return this.domainFacade.scanDevices();	
	}
	

	
	public Device add(IDescriptor devDesc) throws MiddlewareException {
		return this.domainFacade.addDevice(devDesc);
	}

	public void execute(Device device, Object id, Object name) throws MiddlewareException {
		device.callFunction(id, name);	
	}
}
