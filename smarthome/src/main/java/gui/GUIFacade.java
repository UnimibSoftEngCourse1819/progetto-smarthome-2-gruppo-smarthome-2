package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import domain.DomainFacade;
import domain.IDescriptor;
import domain.IDevice;
import domain.IFunction;
import domain.SmartHome;
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
	
	public void showDevice (IDevice device) {
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

	public Collection<IDescriptor> scan() throws FileNotFoundException, IOException, ParseException, Exception {
			return this.domainFacade.scanDevices();	
	}
	

	@Override
	public IDevice add(IDescriptor devDesc) throws FileNotFoundException, IOException, ParseException {
		return this.domainFacade.addDevice(devDesc);
	}

}