package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exceptions.MiddlewareException;

public class HomeGUI extends JPanel{

	
	private JFrame frame; 
	private GUIFacade guiFacade;



	
	public JFrame getFrame() {
		return this.frame;
	}
	


	public HomeGUI() throws MiddlewareException {
		this.guiFacade = GUIFacade.getInstance();
		initialize();
	}

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
					java.util.logging.Logger.getLogger("guilogger").log(Level.WARNING,e1.getMessage(), e1);

				}
			}
		});
		btnScan.setBounds(85, 442, 162, 25);
		frame.getContentPane().add(btnScan);
		
		JButton btnShowDev = new JButton("SMART-HOME");
		if(guiFacade.getDev()==null)
			btnShowDev.setEnabled(false);
		btnShowDev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					guiFacade.showSmartHome();
					frame.setVisible(false);
					
				} catch (Exception e1) {
					java.util.logging.Logger.getLogger("guilogger").log(Level.WARNING,e1.getMessage(), e1);

				}
			}
		});
		
		btnShowDev.setBounds(300, 442, 162, 25);
		frame.getContentPane().add(btnShowDev);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("./src/image/smart-home.png"));
		lblNewLabel.setBounds(24, 13, 503, 416);
		frame.getContentPane().add(lblNewLabel);
					
	}
}
