package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import domain.Device;
import domain.ICommand;
import domain.IDevice;
import domain.IFunction;
import domain.Operation;
import domain.Property;
import domain.TagFunction;
import exceptions.NoOperationException;

public class DeviceGUI extends JPanel {
	private static final String GUILOGGER = "guiLogger";
	public JFrame frame;
	private GUIFacade guiFacade;
	
	public DeviceGUI(IDevice device) {
		this.guiFacade = GUIFacade.getInstance();
		initialize(device);
	}

	private void initialize(IDevice device) {
		frame = new JFrame();
		frame.setBounds(100, 100, 562, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDeviceName = new JLabel(device.getDescriptor().getName().toString());
		lblDeviceName.setBounds(38, 20, 200, 16);
		frame.getContentPane().add(lblDeviceName);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				guiFacade.show();
			}
		});
		btnBack.setBounds(38, 417, 97, 25);
		frame.getContentPane().add(btnBack);
		
		
		

		int posOp = 0;
		int posPr = 0;
		
		
	try {
			ArrayList<Operation> operations = (ArrayList<Operation>) device.getOperations();
			for (Operation op : operations) {
				if (posOp==0) {
					JLabel lblOperation = new JLabel("Do something with your device!");
					lblOperation.setBounds(38, 50, 200, 16);
					Border blackline = BorderFactory.createLineBorder(Color.black);
					lblOperation.setBorder(blackline);
					frame.getContentPane().add(lblOperation);
				}
				posOp+=20;
				JButton btnOperation = new JButton(op.getName().toString());
				btnOperation.setBounds(38, 50 + posOp, 180, 16);
				frame.getContentPane().add(btnOperation);
				btnOperation.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							guiFacade.execute(device, op.getFunctionId(), op.getName());
							frame.setVisible(false);
							guiFacade.showResult(device,op);			
						} catch (Exception e1) {
							java.util.logging.Logger.getLogger(GUILOGGER).log(Level.WARNING,e1.getMessage(), e1);
						}
					}
				});
			}
	}
				catch(NoOperationException e) {
					java.util.logging.Logger.getLogger(GUILOGGER).log(Level.WARNING,e.getMessage(), e);
				}	
	
	
	
	ArrayList<Property> properties = (ArrayList<Property>) device.getProperties();
	for (Property pr : properties) {
		if (posPr==0) {
			JLabel lblOperation = new JLabel("check the satus of your device!");
			lblOperation.setBounds(300, 50, 200, 16);
			Border blackline = BorderFactory.createLineBorder(Color.black);
			lblOperation.setBorder(blackline);
			frame.getContentPane().add(lblOperation);
		}
		posPr+=20;
		JButton btnOperation = new JButton(pr.getName().toString());
		btnOperation.setBounds(300, 50 + posPr, 180, 16);
		frame.getContentPane().add(btnOperation);
		btnOperation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					guiFacade.execute(device, pr.getFunctionId(), pr.getName());
					frame.setVisible(false);
					guiFacade.showResult(device,pr);			
				} catch (Exception e1) {
					java.util.logging.Logger.getLogger(GUILOGGER).log(Level.WARNING,e1.getMessage(), e1);
				}
			}
		});
	}
}
	
}

