package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Device;
import exceptions.MiddlewareException;

public class MySmartHomeGUI extends JPanel{

	private JFrame frame;

	private static final String GUILOGGER = "guiLogger";
	

	private GUIFacade guiFacade;


	/**
	 * Create the application.
	 * @throws MiddlewareException 
	 */
	public MySmartHomeGUI() throws MiddlewareException {
		this.guiFacade = GUIFacade.getInstance();
		
		initialize();
	}

	public JFrame getFrame() {
		return this.frame;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		guiFacade.initializeDimension(this.frame);
		
		JLabel lblTitle = new JLabel("Welcome to your Smart-Home!");
		lblTitle.setBounds(20, 20, 400, 16);
		frame.getContentPane().add(lblTitle);
		
		Collection<Device> devices = guiFacade.getDev();
		int pos = 0;
		for (Device device : devices) {	
			JLabel lblDevice = new JLabel(device.getDescriptor().getName().toString());
			lblDevice.setBounds(20, 60 + pos, 400, 16);
			frame.getContentPane().add(lblDevice);
			
			JButton btnInfo = new JButton("Info");
			btnInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						guiFacade.showDevice(device);
						frame.setVisible(false);
						
					} catch (Exception e1) {
						java.util.logging.Logger.getLogger(GUILOGGER).log(Level.WARNING,e1.getMessage(), e1);

					}
				}
			});
			
			btnInfo.setBounds(120, 60 + pos, 70, 25);
			frame.getContentPane().add(btnInfo);
			pos+=40;
		}
		
		JButton btnScan = new JButton("Scan other devices");
		btnScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					guiFacade.show(guiFacade.scan());
					frame.setVisible(false);
					
				} catch (Exception e1) {
					java.util.logging.Logger.getLogger(GUILOGGER).log(Level.WARNING,e1.getMessage(), e1);

				}
			}
		});
		
		btnScan.setBounds(20, 400, 162, 25);
		frame.getContentPane().add(btnScan);
	}

}
