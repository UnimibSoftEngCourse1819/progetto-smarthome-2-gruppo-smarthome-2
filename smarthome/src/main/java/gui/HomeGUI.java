package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeGUI extends JPanel{

	
	private JFrame frame; 
	private GUIFacade guiFacade;

	/**
	 * Create the application.
	 */
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public HomeGUI() {
		this.guiFacade = GUIFacade.getInstance();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		guiFacade.initializeDimension(this.frame);
		
		JButton btnScan = new JButton("Scan your devices!");
		btnScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					guiFacade.show(guiFacade.scan());
					frame.setVisible(false);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					java.util.logging.Logger.getLogger("guilogger").log(Level.WARNING,e1.getMessage(), e1);

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
					java.util.logging.Logger.getLogger("guilogger").log(Level.WARNING,e1.getMessage(), e1);

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
