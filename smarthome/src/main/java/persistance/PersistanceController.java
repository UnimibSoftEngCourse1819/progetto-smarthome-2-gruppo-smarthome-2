package persistance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONObject;

import exceptions.MiddlewareException;
import middleware.converters.Converter;

public class PersistanceController {
	
	private File jsonDB;
	private Path path;
	private static final String DBNAME = "JsonDB.json";
	private boolean creationState;
	private Converter conv = new Converter();
	
	public PersistanceController() throws MiddlewareException{
		this.creationState=false;
		this.path = Paths.get("").toAbsolutePath();
		this.jsonDB = this.createOrGetFile();
	}
	
	public JSONObject getJsonObjectFile() throws MiddlewareException {
		
			return this.conv.parseJSON(this.jsonDB);
	}
	
	public void saveToFile(JSONObject toSave) throws MiddlewareException {
		try (FileWriter file = new FileWriter(this.jsonDB.getAbsolutePath()))
		{
		file.write(toSave.toJSONString());
		file.flush();
		}
		catch(IOException e) {
			throw new MiddlewareException(e);
		}
		
		
	}

	private File createOrGetFile() throws MiddlewareException {
		File f = new File(this.path.toString() +"/"+this.DBNAME);
		if(!f.exists())
			try {
				this.creationState=f.createNewFile();
			}
			catch(IOException e){
				throw new MiddlewareException(e);
			}
		return f;
	}
	
	
}
