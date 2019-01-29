package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.DeviceDescriptor;
import domain.IDescriptor;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.border.Border;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JMenuBar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ScanDeviceGUI extends JPanel {

	public JFrame frame;
	private GUIFacade guiFacade;

	
	/**
	 * Create the application.
	 */
	public ScanDeviceGUI(Collection <DeviceDescriptor> descs) {
		this.guiFacade = new GUIFacade();
		initialize(descs);
	}
	
	public ScanDeviceGUI() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Collection <DeviceDescriptor> descs) {
		frame = new JFrame();
		frame.setBounds(100, 100, 562, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		if (descs.isEmpty()) {
			JLabel lbl = new JLabel("No devices found. Ensure they are connected to the net or try later.");
			lbl.setBounds(78, 176, 56, 16);
			frame.getContentPane().add(lbl);
		}
		else {
			
			JLabel lblTitle = new JLabel("Here are the devices i found. Add them to your Smart-Home!");
			lblTitle.setBounds(20, 20, 400, 16);
			frame.getContentPane().add(lblTitle);
			int pos = 0;
			for (IDescriptor dd : descs) {
				JLabel lbl = new JLabel(dd.getName().toString());
				lbl.setBounds(100, 100+pos, 160, 16);
				Border blackline = BorderFactory.createLineBorder(Color.black);
				lbl.setBorder(blackline);
				frame.getContentPane().add(lbl);
				
				JButton btn = new JButton("Aggiungi!");
				btn.setBounds(290, 100+pos, 130, 16);
				frame.getContentPane().add(btn);
				pos+=20;
				
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							System.out.println(dd);
							guiFacade.showDevice(guiFacade.add(dd));
							frame.setVisible(false);
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		}	
	}
}
