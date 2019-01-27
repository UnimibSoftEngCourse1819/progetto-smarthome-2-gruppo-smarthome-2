package unimib.gruppo2.smarthome;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import domain.DomainFacade;
import gui.HomeGUI;

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
        
    }
}
