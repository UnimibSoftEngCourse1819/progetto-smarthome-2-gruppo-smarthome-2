package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.IDevice;

public class DeviceGUI extends JPanel {

	public JFrame frame;

	/**
	 * Create the application.
	 */
	public DeviceGUI(IDevice device) {
		initialize(device);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(IDevice device) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
