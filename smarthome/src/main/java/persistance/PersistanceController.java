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
import middleware.MiddlewareException;
import middleware.converters.Converter;

public class PersistanceController {
	
	private File jsonDB;
	private Path path;
	private static final String DBNAME = "JsonDB.json";
	private Converter conv = new Converter();
	
	public PersistanceController() throws IOException{
		this.path = Paths.get("").toAbsolutePath();
		this.jsonDB = this.createOrGetFile();
	}
	
	public void saveToFile(IDescriptor desc) throws IOException, MiddlewareException{
		System.out.println(this.jsonDB.getAbsolutePath());
		JSONObject obj = this.conv.parseJSON(this.jsonDB);
		JSONObject toSave = this.mapObject(desc);
		for(Object key : toSave.keySet() ){
			if(!obj.containsKey(key))
				obj.put(key, toSave.get(key));
		}
		FileWriter file = new FileWriter(this.jsonDB.getAbsolutePath());
		file.write(obj.toJSONString());
		file.flush();
	}

	private File createOrGetFile() throws IOException {
		File f = new File(this.path.toString() +"/"+this.DBNAME);
		if(!f.exists())
			f.createNewFile();
		return f;
	}
	
	private JSONObject mapObject(IDescriptor desc){
		JSONObject obj = new JSONObject();
		obj.put(desc.getId(), desc.getName());
		return obj;
	}
	
	public Collection<IDescriptor> convertToIDescriptors() throws MiddlewareException{
		JSONObject obj = conv.parseJSON(jsonDB);
		System.out.println(obj);
		Collection<IDescriptor> descs = new ArrayList<IDescriptor>();
		for(Object key : obj.keySet())
		descs.add(new DeviceDescriptor(key,obj.get(key)));
		return descs;
	}

}
