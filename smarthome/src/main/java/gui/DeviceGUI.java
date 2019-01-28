package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.Device;
import domain.ICommand;
import domain.IDevice;
import domain.IFunction;
import domain.TagFunction;

import javax.swing.JSpinner;
import javax.swing.border.Border;
import javax.swing.JLabel;

public class DeviceGUI extends JPanel {

	public JFrame frame;

	/**
	 * Create the application.
	 */
	public DeviceGUI(Device device) {
		initialize(device);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Device device) {
		frame = new JFrame();
		frame.setBounds(100, 100, 562, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDeviceName = new JLabel(device.getDescriptor().getName().toString());
		lblDeviceName.setBounds(38, 20, 56, 16);
		frame.getContentPane().add(lblDeviceName);
		
		
		//nome device
		//device.getDescriptor().getName();		
		//spaziare tra le funzioni di un device...
		
		
		
		int posOp = 0;
		int posPr = 0;
		for (IFunction fctn : device.getFunctions()) {
			
			for (ICommand cmd : fctn.getCommands()) {
				//System.out.println(cmd.getTag().toString());
				//System.out.println(new TagFunction("operation.name"));
				if (cmd.getTag().toString().equals(new TagFunction("operation.name").toString())) {
					if (posOp==0) {
						JLabel lblOperation = new JLabel("Do something with your device!");
						lblOperation.setBounds(38, 50, 200, 16);
						Border blackline = BorderFactory.createLineBorder(Color.black);
						lblOperation.setBorder(blackline);
						frame.getContentPane().add(lblOperation);
					}
					posOp+=20;
					JButton btnOperation = new JButton(cmd.getName().toString());
					btnOperation.setBounds(38, 50 + posOp, 120, 16);
					frame.getContentPane().add(btnOperation);
				}	
				else if (cmd.getTag().toString().equals(new TagFunction("property.name").toString())) {
					if (posPr==0) {
						JLabel lblProperty = new JLabel("check the satus of your device!");
						lblProperty.setBounds(300, 50, 200, 16);
						Border blackline = BorderFactory.createLineBorder(Color.black);
						lblProperty.setBorder(blackline);
						frame.getContentPane().add(lblProperty);
					}
					posPr+=20;
					JButton btnProperty = new JButton(cmd.getName().toString());
					btnProperty.setBounds(300, 50 + posPr, 120, 16);				
					frame.getContentPane().add(btnProperty);
				}
			}
		}
	
	}
}
