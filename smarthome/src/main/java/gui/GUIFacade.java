package gui;

import java.awt.EventQueue;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;

import javax.swing.JPanel;

import domain.Device;
import domain.DeviceDescriptor;
import domain.DomainFacade;
import domain.ICommand;
import domain.IDescriptor;
import domain.IDevice;
import javafx.util.Pair;
import middleware.MiddlewareException;


public class GUIFacade implements IGUIFacade {
	private static final String GUILOGGER = "guiLogger";
	private DomainFacade domainFacade;
	private static GUIFacade instance = new GUIFacade();
	
	public GUIFacade(){	
		this.domainFacade = new DomainFacade();
		
	}
	
	public static GUIFacade getInstance() {
			return instance;
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
					java.util.logging.Logger.getLogger(GUILOGGER).log(Level.WARNING,e.getMessage(), e);

				}
			}
		});
	}
	
	public void show (Collection <DeviceDescriptor> descs) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScanDeviceGUI scanPage = new ScanDeviceGUI(descs);
					
					JPanel p = new JPanel();
					p.add(scanPage);
					scanPage.frame.setVisible(true);
					
				} catch (Exception e) {
					java.util.logging.Logger.getLogger(GUILOGGER).log(Level.WARNING,e.getMessage(), e);

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
					java.util.logging.Logger.getLogger(GUILOGGER).log(Level.WARNING,e.getMessage(), e);

				}
			}
		});
	}

	public void showResult(IDevice device, ICommand command) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					StateGUI statePage = new StateGUI(device, command);
					JPanel p = new JPanel();
					p.add(statePage);
					statePage.frame.setVisible(true);
					
					
				} catch (Exception e) {
					java.util.logging.Logger.getLogger(GUILOGGER).log(Level.WARNING,e.getMessage(), e);

				}
			}
		});
		
	}
	
	public Collection<DeviceDescriptor> scan() throws MiddlewareException {
			return this.domainFacade.scanDevices();	
	}
	

	
	public IDevice add(IDescriptor devDesc) throws MiddlewareException {
		return this.domainFacade.addDevice(devDesc);
	}

	public void execute(IDevice device, Object id, Object name) throws MiddlewareException {
		device.callFunctionCommand(id, name);	
	}

	
}
