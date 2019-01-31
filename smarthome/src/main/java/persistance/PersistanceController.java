package persistance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import org.json.simple.JSONObject;

import adapters.DescriptorAdapter;
import domain.DeviceDescriptor;
import domain.IDescriptor;
import exceptions.MiddlewareException;
import middleware.converters.Converter;

public class PersistanceController {
	
	private File jsonDB;
	private Path path;
	private static final String DBNAME = "JsonDB.json";
	private boolean creationState;
	private Converter conv = new Converter();
	
	public PersistanceController() throws IOException{
		this.creationState=false;
		this.path = Paths.get("").toAbsolutePath();
		this.jsonDB = this.createOrGetFile();
	}
	
	public void saveToFile(IDescriptor desc) throws IOException, MiddlewareException{
		//System.out.println(this.jsonDB.getAbsolutePath());
		JSONObject actualDB = new JSONObject();
		JSONObject toSave = this.mapObject(desc);
		if(this.jsonDB.length() != 0)
			actualDB = this.conv.parseJSON(this.jsonDB);
		for(Object key : toSave.keySet() ){
			if(!actualDB.containsKey(key))
				actualDB.put(key, toSave.get(key));
			}
		FileWriter file = new FileWriter(this.jsonDB.getAbsolutePath());
		try {
		file.write(actualDB.toJSONString());
		file.flush();
		}
		catch(Exception e) {}
		finally {
			file.close();
		
		}
		
	}

	private File createOrGetFile() throws IOException {
		File f = new File(this.path.toString() +"/"+this.DBNAME);
		if(!f.exists())
				this.creationState=f.createNewFile();
		return f;
	}
	
	private JSONObject mapObject(IDescriptor desc){
		JSONObject obj = new JSONObject();
		obj.put(desc.getId(), desc.getName());
		return obj;
	}
	
	public Collection<IDescriptor> convertToIDescriptors() throws MiddlewareException{
		JSONObject actualDB = new JSONObject();
		if(this.jsonDB.length() != 0)
			actualDB = conv.parseJSON(jsonDB);
			//throw new MiddlewareException(new EmptyDbException());
		//JSONObject obj = conv.parseJSON(jsonDB);
		//System.out.println(obj);
		Collection<IDescriptor> descs = new ArrayList<IDescriptor>();
		for(Object key : actualDB.keySet())
			descs.add(new DeviceDescriptor(key,actualDB.get(key)));
		return descs;
	}

}
