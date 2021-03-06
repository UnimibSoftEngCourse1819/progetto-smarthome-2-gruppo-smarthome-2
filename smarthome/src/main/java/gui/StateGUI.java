package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.ICommand;
import domain.IDevice;
import domain.Operation;
import exceptions.MiddlewareException;

public class StateGUI extends JPanel{

	private JFrame frame;
	private GUIFacade guiFacade;
	private static final String GUILOGGER = "guiLogger";
	
	/**
	 * Create the application.
	 * @param command 
	 * @param cmd 
	 * @throws MiddlewareException 
	 */
	public StateGUI(IDevice device, ICommand command) throws MiddlewareException {
		initialize(device, command);
		this.guiFacade = GUIFacade.getInstance();
		
	}

	public JFrame getFrame() {
		return this.frame;
	}

	
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
				guiFacade.showDevice(device);
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
		
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
		    @Override
		    public void run()
		    {
		        try {
					guiFacade.save();
				} catch (MiddlewareException | IOException e1) {
					java.util.logging.Logger.getLogger(GUILOGGER).log(Level.WARNING,e1.getMessage(), e1);
				}
		    }
		});

	}

	private void createPropertyState(IDevice device, ICommand command) {
		Map<Object,Object> attribute = device.getAttributeOfAProperty(command.getFunctionId(), command.getName());
		int pos = 0;
		
			for (Object a : attribute.keySet()) {
				
			JLabel lblKey = new JLabel(a.toString());
			lblKey.setBounds(60, 10 + pos, 200, 160);
			frame.getContentPane().add(lblKey);
			
				if (a.toString().equals("timestamp")) {
					 long l = Long.parseLong(attribute.get(a).toString());
					 String date1 = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date(l * 1000));
					 JLabel lblValue = new JLabel(date1);
					 lblValue.setBounds(200, 10 + pos, 300, 160);
					 frame.getContentPane().add(lblValue);
					 pos+=20;
				}
				else {
					 JLabel lblValue = new JLabel(attribute.get(a).toString());
					 lblValue.setBounds(200, 10 + pos, 300, 160);
					 frame.getContentPane().add(lblValue);
					 pos+=20;
				}
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
				
				if (a.toString().equals("timestamp")) {
					 long l = Long.parseLong(map.get(a).toString());
					 String date1 = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date(l * 1000));
					 JLabel lblValue = new JLabel(date1);
					 lblValue.setBounds(200, 10 + pos, 300, 160);
					 frame.getContentPane().add(lblValue);
					 pos+=20;
				}
					else {
					JLabel lblValue = new JLabel(map.get(a).toString());
					lblValue.setBounds(200, 10 + pos, 300, 160);
					frame.getContentPane().add(lblValue);
					pos+=20;
					}
				}
			}
	}
}
