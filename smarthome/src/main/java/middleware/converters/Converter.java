package middleware.converters;

import java.io.File;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import middleware.MiddlewareException;


public class Converter implements IConverter {
	
	public static final String RESULT = "result";
	
	public JSONArray convertToJsonArray(JSONObject obj){
			return (JSONArray) obj.get(RESULT);	
		 }
	
	public JSONObject convertToJsonObject(JSONObject obj) {
		return (JSONObject) obj.get(RESULT);
		
	}
	
	public JSONObject parseJSON(File f) throws MiddlewareException{
		 return (new Parser().parseJSONFile(f));
		 
	}

public boolean isJSONObject(JSONObject ob){
	Object res = ob.get(RESULT);
	if(res instanceof JSONObject)
		return true;
	return false;
}
	
}
