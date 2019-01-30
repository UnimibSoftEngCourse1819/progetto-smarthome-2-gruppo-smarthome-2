package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Device;
import domain.ICommand;
import domain.IDevice;
import domain.Operation;
import javafx.util.Pair;

public class StateGUI extends JPanel{

	public JFrame frame;
	private DeviceGUI deviceGUI;
	
	/**
	 * Create the application.
	 * @param command 
	 * @param cmd 
	 */
	public StateGUI(IDevice device, ICommand command) {
		initialize(device, command);
		this.deviceGUI = new DeviceGUI(device);
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(IDevice device, ICommand command) {
		frame = new JFrame();
		frame.setBounds(100, 100, 562, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Check the current state of your device");
		lblTitle.setBounds(40, 25, 250, 16);
		frame.getContentPane().add(lblTitle);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				deviceGUI.frame.setVisible(true);
			}
		});
		btnBack.setBounds(40, 386, 97, 25);
		frame.getContentPane().add(btnBack);
		
		
		
		if (command instanceof Operation) {
		
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
		
		else {
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
			
		
		
		
		
		
		
		
		
		
		
		/*
		if (cmd instanceof Operation) {
			int pos = 0;
			for (Pair<Object, Object> s : state.keySet()) {
				
				
				for (Object mapKey : state.get(s).keySet()) {
					
					
					if (((Operation) cmd).getFunctionId().toString().equals(s.getKey().toString())) {
						JLabel lblKey = new JLabel(mapKey.toString() + " :");
						lblKey.setBounds(60, 10 + pos, 200, 160);
						frame.getContentPane().add(lblKey);
						JLabel lblValue = new JLabel(state.get(s).get(mapKey).toString());
						lblValue.setBounds(200, 10 + pos, 300, 160);
						frame.getContentPane().add(lblValue);
						pos+=20;
					}			
				}
			}	
		}
		else {
		int pos = 0;
		for (Pair<Object, Object> s : state.keySet()) {
			
			
			for (Object mapKey : state.get(s).keySet()) {
				
				
				if (cmd.getName().toString().equals(s.getValue().toString())) {
					JLabel lblKey = new JLabel(mapKey.toString() + " :");
					lblKey.setBounds(60, 10 + pos, 200, 160);
					frame.getContentPane().add(lblKey);
					JLabel lblValue = new JLabel(state.get(s).get(mapKey).toString());
					lblValue.setBounds(200, 10 + pos, 300, 160);
					frame.getContentPane().add(lblValue);
					pos+=20;
				}			
			}
		}	
	}*/
	} //end method
}
