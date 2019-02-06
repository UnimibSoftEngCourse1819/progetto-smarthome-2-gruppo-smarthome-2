package gui;

import java.awt.EventQueue;
import java.util.Collection;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.Device;
import domain.DeviceDescriptor;
import domain.DomainFacade;
import domain.ICommand;
import domain.IDescriptor;
import domain.IDevice;
import exceptions.MiddlewareException;


public class GUIFacade implements IGUIFacade {
	private static final String GUILOGGER = "guiLogger";
	private DomainFacade domainFacade;
	
		
	private static GUIFacade instance;
	
	
	private GUIFacade() throws MiddlewareException{	
		try {
			this.domainFacade = new DomainFacade();
		}
		catch(MiddlewareException e) {
			java.util.logging.Logger.getLogger(GUILOGGER).log(Level.WARNING,e.getMessage(), e);

		}
		
	}
	
	public static void initializeDimension(JFrame frame) {
		frame.setBounds(100, 100, 562, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	public static GUIFacade getInstance() throws MiddlewareException {
		if (instance == null )
			return new GUIFacade();
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
					home.getFrame().setVisible(true);
					
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
					scanPage.getFrame().setVisible(true);
					
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
					devicePage.getFrame().setVisible(true);
					
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
					statePage.getFrame().setVisible(true);
					
					
				} catch (Exception e) {
					java.util.logging.Logger.getLogger(GUILOGGER).log(Level.WARNING,e.getMessage(), e);

				}
			}
		});
		
	}
	
	@Override
	public void showSmartHome() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MySmartHomeGUI mySmartHome = new MySmartHomeGUI();
					
					JPanel p = new JPanel();
					p.add(mySmartHome);
					mySmartHome.getFrame().setVisible(true);
					
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

	public Collection<Device> getDev() {
		return domainFacade.getDevices();
		
	}

	
}
