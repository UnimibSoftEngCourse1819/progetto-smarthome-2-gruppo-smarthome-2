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
import domain.IFunction;


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
		});*/
        
    	/************Scan Device************/
    	List<DeviceDescriptor> res = new ArrayList<>();
    	DomainFacade df = new DomainFacade();
    	 df.scanDevices();
    	 res = df.getDeviceDescriptors();
    	 System.out.println("Array Size " + res.size());
    	 for(DeviceDescriptor dd : res)
    		 System.out.println(dd);
    	 
    	 
    	 
    	 /************Add Device************/
    	 System.out.println("************ADD DEVICE******************");
    	 Device d = df.addDevice(res.get(9));
    	 for(IFunction f : d.getFunctions())
    		 System.out.println(f);
    	 
    	 
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
