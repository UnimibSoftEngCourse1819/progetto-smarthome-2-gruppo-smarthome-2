package gui;

import java.awt.EventQueue;
import java.security.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;

import domain.Device;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StateGUI extends JPanel{

	public JFrame frame;
	private DeviceGUI deviceGUI;
	
	/**
	 * Create the application.
	 */
	public StateGUI(Device device,Map<Object, Object> state) {
		initialize(state);
		this.deviceGUI = new DeviceGUI(device);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Map<Object, Object> state) {
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
		
		int pos = 0;
		for (Object s : state.keySet()) {
			System.out.println(state.get(s));
			JLabel lblKey = new JLabel(s.toString() + " :");
			lblKey.setBounds(60, 60 + pos, 200, 160);
			frame.getContentPane().add(lblKey);
			
			if (s.toString().equals("timestamp")) {
				long l = Long.parseLong(state.get("timestamp").toString());
				String date1 = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date(l * 1000));
				System.out.println(date1);
				JLabel lblValue = new JLabel(date1);
				lblValue.setBounds(200, 60 + pos, 300, 160);
				frame.getContentPane().add(lblValue);
				pos+=20;
			}
			else {
				JLabel lblValue = new JLabel(state.get(s).toString());
				lblValue.setBounds(200, 60 + pos, 300, 160);
				frame.getContentPane().add(lblValue);
				pos+=20;
			}
			
		}

		
	}
}
