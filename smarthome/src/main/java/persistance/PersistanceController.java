package persistance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import adapters.DescriptorAdapter;
import domain.DeviceDescriptor;
import domain.IDescriptor;
import domain.TagDevice;
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
			System.out.println(this.conv.parseJSON(this.jsonDB));
			return this.conv.parseJSON(this.jsonDB);
	}
	
	public void saveToFile(List<IDescriptor> desc) throws MiddlewareException {
		JSONObject actualDB = new JSONObject();
		actualDB.put("result", new JSONArray());
		
		
		JSONArray devicesToSave = new JSONArray();
		
		for (IDescriptor d : desc) {
			JSONObject toSave = this.mapObject(d); // oggetto che vuoi aggiungere al file
			devicesToSave.add(toSave);
		}
		
		actualDB.put("result", devicesToSave);
		
		
		
		
		/*if(this.jsonDB.length() != 0)
			actualDB = this.conv.parseJSON(this.jsonDB);
			JSONArray appoggio = (JSONArray) actualDB.get("result");		
			// TODO
			// non sono sicuro  se il jsonarray contiene l'oggetto toSave...va bene contains??
			if (!appoggio.contains(toSave))
				appoggio.add(toSave);
			actualDB.remove("result"); // svuoto array */
		
			 // riempio
		
		try {
		FileWriter file = new FileWriter(this.jsonDB.getAbsolutePath());
		file.write(actualDB.toJSONString());
		file.flush();
		file.close();
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
	
	private JSONObject mapObject(IDescriptor desc){
		JSONObject obj = new JSONObject();
		obj.put(new TagDevice("UID").getTagValue(), desc.getId());
		obj.put(new TagDevice("name").getTagValue(), desc.getName());
		return obj;
	}
	
	public Collection<IDescriptor> convertToIDescriptors() throws MiddlewareException{
		JSONObject actualDB = new JSONObject();
		if(this.jsonDB.length() != 0)
			actualDB = conv.parseJSON(jsonDB);
		Collection<IDescriptor> descs = new ArrayList<IDescriptor>();
		for(Object key : actualDB.keySet())
			descs.add(new DeviceDescriptor(key,actualDB.get(key)));
		return descs;
	}

}
