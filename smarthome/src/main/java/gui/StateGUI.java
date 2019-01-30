package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.ICommand;
import domain.IDevice;
import domain.Operation;

public class StateGUI extends JPanel{

	private JFrame frame;
	private GUIFacade guiFacade;
	private DeviceGUI deviceGUI;
	
	/**
	 * Create the application.
	 * @param command 
	 * @param cmd 
	 */
	public StateGUI(IDevice device, ICommand command) {
		initialize(device, command);
		this.guiFacade = new GUIFacade();
		this.deviceGUI = new DeviceGUI(device);
	}

	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(IDevice device, ICommand command) {
		frame = new JFrame();
		guiFacade.initializeDimension(this.frame);
		
		JLabel lblTitle = new JLabel("Check the current state of your device");
		lblTitle.setBounds(40, 25, 250, 16);
		frame.getContentPane().add(lblTitle);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				deviceGUI.getFrame().setVisible(true);
			}
		});
		btnBack.setBounds(40, 386, 97, 25);
		frame.getContentPane().add(btnBack);
		
		
		
		if (command instanceof Operation) {
		
		createOperationState(device, command);
		
		}
		
		else {
			
		createPropertyState(device, command);
			
		}
			
	}

	private void createPropertyState(IDevice device, ICommand command) {
		Map<Object,Object> attribute = device.getAttributeOfAProperty(command.getFunctionId(), command.getName());
		int pos = 0;
		
			for (Object a : attribute.keySet()) {
				
			JLabel lblKey = new JLabel(a.toString());
			lblKey.setBounds(60, 10 + pos, 200, 160);
			frame.getContentPane().add(lblKey);
			
			JLabel lblValue = new JLabel(attribute.get(a).toString());
			lblValue.setBounds(200, 10 + pos, 300, 160);
			frame.getContentPane().add(lblValue);
			pos+=20;
			}
	} 

	private void createOperationState(IDevice device, ICommand command) {
		ArrayList<Map<Object,Object>> properties = 
				(ArrayList<Map<Object, Object>>) 
					device.getParametersOfThisFunction(command.getFunctionId());
		
			int pos = 0;
			
			for (Map<Object, Object> map : properties) {
				for(Object a : map.keySet()) {
				
				JLabel lblKey = new JLabel(a.toString());
				lblKey.setBounds(60, 10 + pos, 200, 160);
				frame.getContentPane().add(lblKey);
				
				JLabel lblValue = new JLabel(map.get(a).toString());
				lblValue.setBounds(200, 10 + pos, 300, 160);
				frame.getContentPane().add(lblValue);
				pos+=20;
				}
			}
	}
}
