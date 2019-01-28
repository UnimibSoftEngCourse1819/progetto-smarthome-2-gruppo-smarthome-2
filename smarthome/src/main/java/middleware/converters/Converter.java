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
	
	
	public JSONArray convertToJsonArray(JSONObject obj) throws MiddlewareException {
				//JSONObject obj = (new Parser().parseJSONFile(f));
			JSONArray ja = (JSONArray) obj.get("result");	
			return ja;
		 }
	
	public JSONObject convertToJsonObject(JSONObject obj) throws MiddlewareException {
		//JSONObject obj = (new Parser().parseJSONFile(f));
		obj = (JSONObject) obj.get("result");
		return obj;
 }
	
	public JSONObject parseJSON(File f) throws MiddlewareException{
		 JSONObject obj = (new Parser().parseJSONFile(f));
		 return obj;
	}

public boolean isJSONObject(JSONObject ob){
	Object res = ob.get("result");
	if(res instanceof JSONObject)
		return true;
	else
		return false;
}
	



}
