package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	public ScanDeviceGUI(Collection <IDescriptor> descs) {
		this.guiFacade = new GUIFacade();
		initialize(descs);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Collection <IDescriptor> descs) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		if (descs.isEmpty()) {
			JLabel lbl = new JLabel("No devices found. Ensure they are connected to the net or try later.");
			lbl.setBounds(78, 176, 56, 16);
			frame.getContentPane().add(lbl);
		}
		else {
			
			int pos = 0;
			for (IDescriptor dd : descs) {
				JLabel lbl = new JLabel(dd.getName().toString());
				lbl.setBounds(10, 10+pos, 160, 16);
				Border blackline = BorderFactory.createLineBorder(Color.black);
				lbl.setBorder(blackline);
				frame.getContentPane().add(lbl);
				
				JButton btn = new JButton("Aggiungi!");
				btn.setBounds(185, 10 + pos, 130, 16);
				frame.getContentPane().add(btn);
				pos+=20;
				
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							guiFacade.showDevice(guiFacade.add(dd));
							
							
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
