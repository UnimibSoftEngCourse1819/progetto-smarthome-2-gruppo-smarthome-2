package unimib.gruppo2.smarthome;


import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import gui.HomeGUI;


public final class App {
    private App() {
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, Exception {
    	
    	
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeGUI homegui  = new HomeGUI();
					
					JPanel p = new JPanel();
					p.add(homegui);
					homegui.getFrame().setVisible(true);
					
				} catch (Exception e) {
					java.util.logging.Logger.getLogger("guilogger").log(Level.WARNING,e.getMessage(), e);

				}
			}
		});
       

    }
}
