package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import domain.DeviceDescriptor;
import domain.IDescriptor;
import exceptions.MiddlewareException;

public class ScanDeviceGUI extends JPanel {

	private JFrame frame;
	private GUIFacade guiFacade;
	private static final String GUILOGGER = "guiLogger";
	
	/**
	 * Create the application.
	 * @throws MiddlewareException 
	 */
	public ScanDeviceGUI(Collection <DeviceDescriptor> descs) throws MiddlewareException {
		this.guiFacade = GUIFacade.getInstance();
		initialize(descs);
	}
	
	public JFrame getFrame() {
		return this.frame;
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Collection <DeviceDescriptor> descs) {
		frame = new JFrame();
		guiFacade.initializeDimension(this.frame);
		
		if (descs.isEmpty()) {
			JLabel lbl = new JLabel("No devices found. Ensure they are connected to the net or try later.");
			lbl.setBounds(78, 176, 56, 16);
			frame.getContentPane().add(lbl);
		}
		else {
			
			JLabel lblTitle = new JLabel("Here are the devices i found.Add them to your Smart-Home!");
			lblTitle.setBounds(20, 20, 400, 16);
			frame.getContentPane().add(lblTitle);
			int pos = 0;
			for (IDescriptor dd : descs) {
				JLabel lbl = new JLabel(dd.getName().toString());
				lbl.setBounds(100, 100+pos, 160, 16);
				Border blackline = BorderFactory.createLineBorder(Color.black);
				lbl.setBorder(blackline);
				frame.getContentPane().add(lbl);
				
				JButton btn = new JButton("Aggiungi!");
				btn.setBounds(290, 100+pos, 130, 16);
				frame.getContentPane().add(btn);
				pos+=20;
				
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							guiFacade.add(dd);
							guiFacade.showSmartHome();
							frame.setVisible(false);
							
						} catch (Exception e1) {
							java.util.logging.Logger.getLogger("guilogger").log(Level.WARNING,e1.getMessage(), e1);

						}
					}
				});
			}
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
		    @Override
		    public void run()
		    {
		        try {
					guiFacade.save();
				} catch (MiddlewareException | IOException e1) {
					java.util.logging.Logger.getLogger(GUILOGGER).log(Level.WARNING,e1.getMessage(), e1);
				}
		    }
		});

		
	}
}
