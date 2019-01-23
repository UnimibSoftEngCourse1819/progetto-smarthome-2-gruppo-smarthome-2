package middleware.converters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Parser {
	
	private JSONParser parser;
	
	
	public Parser(){
		this.parser = new JSONParser();
	}
	
	
	public JSONObject parseJSONFile(File jsonFile) throws FileNotFoundException, IOException, ParseException {
		// parsing file "JSONExample.json" 
        Object obj = parser.parse(new FileReader(jsonFile)); 
        
        // typecasting obj to JSONObject 
        JSONObject jo = (JSONObject) obj; 
		return jo;
	}

}
