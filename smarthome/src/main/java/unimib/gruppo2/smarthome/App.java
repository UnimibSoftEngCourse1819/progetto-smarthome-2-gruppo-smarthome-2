package unimib.gruppo2.smarthome;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import domain.DomainFacade;
import domain.ICommand;
import gui.HomeGUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import domain.Device;
import domain.DeviceDescriptor;
import domain.DomainFacade;
import domain.IDescriptor;
import domain.IDevice;
import domain.IFunction;
import domain.Property;


/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws Exception 
     * @throws ParseException 
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, Exception {
    	
    	/*
    	
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeGUI homegui  = new HomeGUI();
					
					JPanel p = new JPanel();
					p.add(homegui);
					homegui.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        */


    	/*List<DeviceDescriptor> res = new ArrayList<>();

    	
    	/************Scan Device************/
    	
    	List<DeviceDescriptor> res = new ArrayList<>();

    	DomainFacade df = new DomainFacade();
    	 df.scanDevices();
    	 df.scanDevices();
    	 res = df.getDeviceDescriptors();
    	 System.out.println("Array Size " + res.size());
    	 for(DeviceDescriptor dd : res)
    		 System.out.println(dd);
    	 
    	 
    	 
    	 /************Add Device************/
    	
    	 System.out.println("************ADD DEVICE******************");
    	 IDevice d = df.addDevice(res.get(9));
    	 df.scanDevices();
    	 System.out.println("Array Size After Added 1 device " + res.size());
    	 
    	 for(Object fId : d.getFunctionsIds())
    		 System.out.println(fId);
    	 d.initState();
    	 //System.out.println(d.getState());
    	 System.out.println("************AFTER INIT STATE*****************");
    	// for(Object f : d.getFunctionsIds())
    		 System.out.println(d.getOperations());
    		 System.out.println(d.getProperties());
    	 //for(Property p : d.getProperties())
    		 //System.out.println(p);
    	 
    	 
    	 //System.out.println(df.getDeviceDescriptors());
    	 
    	 /*
    	 Device d = df.addDevice(res.get(9));
    	 
    	 d.initState(d.getFunctions());
    	 ArrayList<IFunction> fns =  (ArrayList<IFunction>) d.getFunctions();
    	 ArrayList<ICommand> cms = (ArrayList<ICommand>) fns.get(0).getCommands();
    	d.callFunction(fns.get(0).getId(), cms.get(1).getName());
    	 d.callFunction(fns.get(0).getId(), cms.get(0).getName());
    	 
    	 
    	 System.out.println(d.getDescriptor());
    	 System.out.println(d.getFunctions());
    	 for(ICommand com : fns.get(0).getCommands())
    		 System.out.println(com.getClass());
    	 
    	ArrayList<IFunction> fns =  (ArrayList<IFunction>) d.getFunctions();
    	ArrayList<ICommand> cms = (ArrayList<ICommand>) fns.get(0).getCommands();
    	/cms.get(1).execute();
    	//for(ICommand c : cms)
    		//System.out.println(c);*/

    }
}
