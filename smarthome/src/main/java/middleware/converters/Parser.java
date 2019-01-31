package middleware.converters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import exceptions.MiddlewareException;

public class Parser {
	
	private JSONParser parser;
	
	
	public Parser(){
		this.parser = new JSONParser();
	}
	
	
	public JSONObject parseJSONFile(File jsonFile) throws MiddlewareException {
		JSONObject jo = new JSONObject();
		try {
	        Object obj = parser.parse(new FileReader(jsonFile)); 
	        jo = (JSONObject) obj; 
	        
		}
		catch (IOException | ParseException e){
			throw new MiddlewareException(e); 
		}
		
		return jo;
	}

}
