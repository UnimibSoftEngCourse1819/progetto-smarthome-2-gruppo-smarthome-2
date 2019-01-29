package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import domain.DeviceDescriptor;

import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;
import java.awt.event.ActionEvent;

public class HomeGUI extends JPanel{

	
	public JFrame frame; 
	private GUIFacade guiFacade;

	/**
	 * Create the application.
	 */
	public HomeGUI() {
		this.guiFacade = GUIFacade.getInstance();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 562, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnScan = new JButton("Scan your devices!");
		btnScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					guiFacade.show(guiFacade.scan());
					frame.setVisible(false);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnScan.setBounds(85, 442, 162, 25);
		frame.getContentPane().add(btnScan);
		
		JButton btnShowDev = new JButton("SMART-HOME");
		btnShowDev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShowDev.setBounds(300, 442, 162, 25);
		frame.getContentPane().add(btnShowDev);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("smart-home.png"));
		lblNewLabel.setBounds(24, 13, 503, 416);
		frame.getContentPane().add(lblNewLabel);
		
		ImageIcon imageIcon = new ImageIcon("smart-home.png");
	    
	    
	    
				
	}
}
