package middleware.converters;

import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import domain.IDescriptor;
import domain.TagDevice;

public class PersistanceConverter {
	
	public JSONObject convertToObjectToSave(Collection <IDescriptor> desc) {
	JSONObject actualDB = new JSONObject();
	actualDB.put("result", new JSONArray());

	JSONArray devicesToSave = new JSONArray();
	
	for (IDescriptor d : desc) {
		JSONObject toSave = this.mapObject(d); 
		devicesToSave.add(toSave);
	}
	
	actualDB.put("result", devicesToSave);
	
	return actualDB;
	
}
	private JSONObject mapObject(IDescriptor desc){
		JSONObject obj = new JSONObject();
		obj.put(new TagDevice("UID").getTagValue(), desc.getId());
		obj.put(new TagDevice("name").getTagValue(), desc.getName());
		return obj;
		
	}
	
}

