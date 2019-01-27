package unimib.gruppo2.smarthome;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import domain.Device;
import domain.DeviceDescriptor;
import domain.DomainFacade;
import domain.IDescriptor;

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
       // System.out.println("Hello World!");
    	List<DeviceDescriptor> res = new ArrayList<>();
    	DomainFacade df = new DomainFacade();
    	 df.scanDevices();
    	 res = df.getDeviceDescriptors();
    	 System.out.println(res.size());
    	 
    	 
    	 Device d = df.addDevice(res.get(3));
    	 
    	 System.out.println(d.getDescriptor());
    	 System.out.println(d.getFunctions());
    	 
    }
}
