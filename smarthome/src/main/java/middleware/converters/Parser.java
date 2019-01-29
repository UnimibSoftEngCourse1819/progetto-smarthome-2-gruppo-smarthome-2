package middleware.converters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import middleware.MiddlewareException;

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
		catch (FileNotFoundException e){
			throw new MiddlewareException(e); 
		}
		catch (IOException e){
			throw new MiddlewareException(e);
		}
		catch (ParseException e){
			throw new MiddlewareException(e);
		}
		return jo;
	}

}
