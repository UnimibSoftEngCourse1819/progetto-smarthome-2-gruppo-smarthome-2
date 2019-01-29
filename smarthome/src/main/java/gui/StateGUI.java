package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.security.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;

import domain.Device;
import domain.ICommand;
import domain.Operation;
import javafx.util.Pair;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StateGUI extends JPanel{

	public JFrame frame;
	private DeviceGUI deviceGUI;
	
	/**
	 * Create the application.
	 * @param cmd 
	 */
	public StateGUI(Device device,ICommand cmd, Map<Pair<Object, Object>, Map<Object, Object>> state) {
		initialize(cmd, state);
		this.deviceGUI = new DeviceGUI(device);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ICommand cmd, Map<Pair<Object, Object>, Map<Object, Object>> state) {
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
	}
	}
}
