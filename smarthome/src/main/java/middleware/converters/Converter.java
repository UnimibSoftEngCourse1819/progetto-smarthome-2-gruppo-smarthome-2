package middleware.converters;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.ParseException;

import middleware.MiddlewareException;


public class Converter implements IConverter {
	
	@Override
	public JSONArray convertToJsonArray(File f) throws MiddlewareException {
				JSONObject obj = (new Parser().parseJSONFile(f));
				JSONArray ja = (JSONArray) obj.get("result");	
			return ja;
		 }
	
	public JSONObject convertToJsonObject(File f) throws MiddlewareException {
		JSONObject obj = (new Parser().parseJSONFile(f));
		obj = (JSONObject) obj.get("result");
		return obj;
 }
	
	
	
	
/*
public Collection<ILowObject> convertTo(JSONObject jo,String parameter) throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
		
	  
    JSONArray ja = (JSONArray) jo.get("result");
    
    
    LowObjectCreator creator = new LowObjectCreator();
    
    for (Object element : ja) {
    	creator.setToAdapt((JSONObject) element); 	
        lowObjectCollection.add(creator.convertToLowObject(parameter));
    }
    return lowObjectCollection;
    
}

*/
	



}
